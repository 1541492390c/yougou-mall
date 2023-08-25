package per.ccm.ygmall.order.listener;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.feign.product.feign.ProductFeign;
import per.ccm.ygmall.common.basic.exception.YougouException;
import per.ccm.ygmall.common.basic.response.ResponseCodeEnum;
import per.ccm.ygmall.common.basic.util.ConvertUtils;
import per.ccm.ygmall.order.dto.OrderDTO;
import per.ccm.ygmall.order.enums.OrderStateEnum;
import per.ccm.ygmall.order.service.OrderService;
import per.ccm.ygmall.order.vo.OrderItemVO;
import per.ccm.ygmall.order.vo.OrderVO;
import per.ccm.ygmall.common.rabbitmq.config.RabbitmqConfig;

import java.util.*;

@Component
public class OrderListener {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductFeign productFeign;

    /**
     * 订单15分钟未支付自动取消
     * */
    @RabbitListener(queues = RabbitmqConfig.ORDER_DEAD_LETTER_QUEUE)
    @GlobalTransactional(rollbackFor = Exception.class)
    public void cancelOrder(Long orderId) throws Exception {
        OrderVO orderVO = orderService.getOrderById(orderId);
        if (!ObjectUtils.isEmpty(orderVO) && !orderVO.getIsPay() && ObjectUtils.nullSafeEquals(orderVO.getState(), OrderStateEnum.WAIT_PAY.getValue())) {
            // 更新订单
            OrderDTO orderDTO = ConvertUtils.convertProperties(orderVO, OrderDTO.class);
            orderDTO.setCancelTime(new Date());
            orderDTO.setState(OrderStateEnum.CANCEL.getValue());
            orderDTO.setRemark("订单超过15分钟未支付,自动取消");
            orderService.update(orderDTO);
            // 将取消的订单sku库存返还
            List<OrderItemVO> orderItemList = orderVO.getOrderItemList();
            Map<Long, Integer> skuStockMap = new HashMap<>();
            for (OrderItemVO orderItemVO : orderItemList) {
                skuStockMap.put(orderItemVO.getSkuId(), orderItemVO.getQuantity());
            }
            // 抛异常回滚
            if (!productFeign.updateSkuStock(skuStockMap).responseSuccess()) {
                throw new YougouException(ResponseCodeEnum.SERVER_ERROR_00001);
            }
        }
    }
}
