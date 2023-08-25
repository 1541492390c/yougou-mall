package per.ccm.ygmall.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.api.order.bo.OrderBO;
import per.ccm.ygmall.api.order.bo.OrderItemBO;
import per.ccm.ygmall.api.product.bo.ProductBO;
import per.ccm.ygmall.api.product.bo.SkuBO;
import per.ccm.ygmall.api.product.feign.ProductFeign;
import per.ccm.ygmall.common.basic.exception.YougouException;
import per.ccm.ygmall.common.basic.response.ResponseCodeEnum;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.common.basic.util.ConvertUtils;
import per.ccm.ygmall.common.basic.vo.PageVO;
import per.ccm.ygmall.order.dto.OrderAddrDTO;
import per.ccm.ygmall.order.dto.OrderDTO;
import per.ccm.ygmall.order.dto.OrderItemDTO;
import per.ccm.ygmall.order.entity.Order;
import per.ccm.ygmall.order.enums.OrderStateEnum;
import per.ccm.ygmall.order.mapper.OrderMapper;
import per.ccm.ygmall.order.service.OrderAddrService;
import per.ccm.ygmall.order.service.OrderItemService;
import per.ccm.ygmall.order.service.OrderService;
import per.ccm.ygmall.order.vo.OrderVO;
import per.ccm.ygmall.common.rabbitmq.config.RabbitmqConfig;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderAddrService orderAddrService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ProductFeign productFeign;

    @Autowired
    private RLock rLock;

    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public Long save(OrderDTO orderDTO) throws Exception {
        rLock.lock();
        try {
            // 判断该用户是否存在未支付订单
            if (this.isUnPayOrder(orderDTO.getUserId())) {
                throw new YougouException(ResponseCodeEnum.ORDER_ERROR_D0002);
            }
            // 获取skuID列表
            List<Long> skuIdList = orderDTO.getOrderItemList().stream().map(OrderItemDTO::getSkuId).collect(Collectors.toList());
            // 获取商品内部传输数据列表
            List<ProductBO> productBOList = productFeign.queryProductBOList(skuIdList).getData();
            // 将订单项根据skuID分组
            Map<Long, List<OrderItemDTO>> map = orderDTO.getOrderItemList().stream().collect(Collectors.groupingBy(OrderItemDTO::getSkuId));

            OrderAddrDTO orderAddrDTO = orderDTO.getOrderAddr();
            List<OrderItemDTO> saveOrderItemDTOList = new ArrayList<>();
            // 设置更新的sku库存与skuID
            Map<Long, Integer> skuStockMap = new HashMap<>();
            // 当前订单项总额初始值
            BigDecimal orderTotalAmount = new BigDecimal(0);
            for (ProductBO productBO : productBOList) {
                for (SkuBO skuBO : productBO.getSkuBOList()) {
                    List<OrderItemDTO> orderItemDTOList = map.get(skuBO.getSkuId());
                    // 计算购买数量
                    int quantity = orderItemDTOList.stream().mapToInt(OrderItemDTO::getQuantity).sum();
                    // 计算当前订单项总价值
                    BigDecimal currentItemTotalAmount = this.getCurrentItemTotalAmount(skuBO, quantity);
                    // 计算订单总额
                    orderTotalAmount = orderTotalAmount.add(currentItemTotalAmount);
                    // 新建订单项
                    OrderItemDTO orderItemDTO = new OrderItemDTO();
                    // 设置商品的属性
                    orderItemDTO.setProductId(productBO.getProductId());
                    orderItemDTO.setProductName(productBO.getName());
                    orderItemDTO.setImg(productBO.getCover());
                    // 设置sku的属性
                    orderItemDTO.setSkuId(skuBO.getSkuId());
                    orderItemDTO.setSpecs(skuBO.getSpecs());
                    // 设置订单项属性
                    orderItemDTO.setQuantity(quantity);
                    orderItemDTO.setTotalAmount(currentItemTotalAmount);
                    saveOrderItemDTOList.add(orderItemDTO);
                    // 设置需要更新的skuID与库存
                    skuStockMap.put(skuBO.getSkuId(), (-quantity));
                }
            }
            Order order = new Order();
            // 设置订单号
            order.setOrderNo("YG" + (new DefaultIdentifierGenerator().nextId(order)));
            // 设置用户ID
            order.setUserId(orderDTO.getUserId());
            // 设置订单状态
            order.setState(OrderStateEnum.WAIT_PAY.getValue());
            // 设置订单总金额
            order.setTotalAmount(orderTotalAmount);
            // 保存订单
            orderMapper.insert(order);
            // 保存订单后,为订单项和订单收货地址插入订单ID
            orderAddrDTO.setOrderId(order.getOrderId());
            saveOrderItemDTOList.forEach(item -> item.setOrderId(order.getOrderId()));
            // 保存订单地址
            orderAddrService.save(orderAddrDTO);
            // 保存订单项
            orderItemService.batchSave(saveOrderItemDTOList);
            // 减少商品库存,如果失败,则抛异常回滚
            ResponseEntity<Void> response = productFeign.updateSkuStock(skuStockMap);
            if (!response.responseSuccess()) {
                throw new YougouException(ResponseCodeEnum.getValueOf(response.getCode()));
            }
            //将订单加入延时队列
            rabbitTemplate.convertAndSend(RabbitmqConfig.DIRECT_EXCHANGE, RabbitmqConfig.ORDER_DELAY_ROUTING_KEY, order.getOrderId());
            // 返回订单ID
            return order.getOrderId();
        } finally {
            rLock.unlock();
        }
    }

    @Override
    public OrderBO getOrderBOById(Long orderId) {
        OrderVO orderVO = orderMapper.selectOrderById(orderId);
        // 创建订单内部传输数据
        OrderBO orderBO = ConvertUtils.convertProperties(orderVO, OrderBO.class);
        // 创建订单项内部传输数据
        List<OrderItemBO> orderItemBOList = ConvertUtils.converList(orderVO.getOrderItemList(), OrderItemBO.class);
        orderBO.setOrderItemBOList(orderItemBOList);
        return orderBO;
    }

    @Override
    public OrderVO getOrderById(Long orderId) {
        return orderMapper.selectOrderById(orderId);
    }

    @Override
    public PageVO<OrderVO> getOrderPages(Long userId, Page<Order> page) {
        Page<OrderVO> pageInfo = orderMapper.selectOrderPages(userId, page);
        return new PageVO<>(pageInfo.getTotal(), pageInfo.getRecords());
    }

    @Override
    public void update(OrderDTO orderDTO) {
        Order order = ConvertUtils.convertProperties(orderDTO, Order.class);
        orderMapper.updateById(order);
    }

    /**
     * 判断该用户是否存在未支付订单
     *
     * @param userId 用户ID
     * @return 是否存在未支付订单
     * */
    private Boolean isUnPayOrder(Long userId) {
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getUserId, userId).eq(Order::getIsPay, Boolean.FALSE).eq(Order::getState, OrderStateEnum.WAIT_PAY.getValue());
        return orderMapper.exists(queryWrapper);
    }

    /**
     * 计算当前订单项总额
     *
     * @param skuBO sku内部传输数据
     * @param quantity 数量
     * @return 前订单项总额
     * */
    private BigDecimal getCurrentItemTotalAmount(SkuBO skuBO, int quantity) {
        BigDecimal currentItemTotalAmount = new BigDecimal(0);
        // 判断该sku是否有折扣价格
        if (!ObjectUtils.isEmpty(skuBO.getDiscountPrice())) {
            currentItemTotalAmount = currentItemTotalAmount.add(skuBO.getDiscountPrice().multiply(new BigDecimal(quantity)));
        } else {
            currentItemTotalAmount = currentItemTotalAmount.add(skuBO.getPrice().multiply(new BigDecimal(quantity)));
        }
        return currentItemTotalAmount;
    }
}
