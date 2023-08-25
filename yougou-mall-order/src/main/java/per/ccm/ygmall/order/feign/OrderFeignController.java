package per.ccm.ygmall.order.feign;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.feign.order.bo.OrderBO;
import per.ccm.ygmall.feign.order.feign.OrderFeign;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.order.entity.Order;
import per.ccm.ygmall.order.enums.OrderStateEnum;
import per.ccm.ygmall.order.service.OrderService;

import java.util.Date;

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
        // 更新订单为已支付
        order.setIsPay(Boolean.TRUE);
        // 更新订单状态为待发货
        order.setState(OrderStateEnum.WAIT_DELIVERY.getValue());
        // 更新订单支付时间
        order.setPayTime(new Date());
        orderService.updateById(order);
        return ResponseEntity.success();
    }
}
