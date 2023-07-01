package per.ccm.ygmall.order.listener;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.api.product.bo.SkuBO;
import per.ccm.ygmall.api.product.feign.ProductFeign;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCodeEnum;
import per.ccm.ygmall.order.enums.OrderStateEnum;
import per.ccm.ygmall.order.service.OrderService;
import per.ccm.ygmall.order.vo.OrderItemVO;
import per.ccm.ygmall.order.vo.OrderVO;
import per.ccm.ygmall.rabbitmq.config.RabbitmqConfig;

import java.util.ArrayList;
import java.util.List;

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
        if (!orderVO.getIsPay() && ObjectUtils.nullSafeEquals(orderVO.getState(), OrderStateEnum.WAIT_PAY.getValue())) {
            List<SkuBO> skuBOList = new ArrayList<>();
            List<OrderItemVO> orderItemList = orderVO.getOrderItemList();
            for (OrderItemVO orderItemVO : orderItemList) {
                SkuBO skuBO = new SkuBO();
                skuBO.setSkuId(orderItemVO.getSkuId());
                skuBO.setSkuStock(orderItemVO.getQuantity());
                skuBOList.add(skuBO);
            }
            // 抛异常回滚
            if (!productFeign.batchUpdateSku(skuBOList).responseSuccess()) {
                throw new YougouException(ResponseCodeEnum.SERVER_ERROR_00001);
            }
        }
    }
}
