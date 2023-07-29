package per.ccm.ygmall.order.feign;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.api.order.bo.OrderBO;
import per.ccm.ygmall.api.order.feign.OrderFeign;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.order.entity.Order;
import per.ccm.ygmall.order.enums.OrderStateEnum;
import per.ccm.ygmall.order.service.OrderService;

@Hidden
@RestController
public class OrderFeignController implements OrderFeign {

    @Autowired
    private OrderService orderService;

    @Override
    public ResponseEntity<OrderBO> getOrderBOById(Long orderId) throws Exception {
        OrderBO orderBO = orderService.getOrderBOById(orderId);
        return ResponseEntity.success(orderBO);
    }

    @Override
    public ResponseEntity<Void> paySuccess(String orderNo) {
        Order order = orderService.getOne(new LambdaQueryWrapper<Order>().eq(Order::getOrderNo, orderNo));
        // 修改订单为已支付
        order.setIsPay(Boolean.TRUE);
        // 修改订单状态为待发货
        order.setState(OrderStateEnum.WAIT_DELIVERY.getValue());
        orderService.updateById(order);
        return ResponseEntity.success();
    }
}
