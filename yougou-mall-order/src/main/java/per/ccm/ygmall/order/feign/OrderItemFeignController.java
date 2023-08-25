package per.ccm.ygmall.order.feign;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.common.basic.util.ConvertUtils;
import per.ccm.ygmall.feign.order.bo.OrderItemBO;
import per.ccm.ygmall.feign.order.feign.OrderItemFeign;
import per.ccm.ygmall.order.entity.OrderItem;
import per.ccm.ygmall.order.service.OrderItemService;

@Hidden
@RestController
public class OrderItemFeignController implements OrderItemFeign {

    @Autowired
    private OrderItemService orderItemService;

    @Override
    public ResponseEntity<Void> updateOrderItem(OrderItemBO orderItemBO) {
        OrderItem orderItem = ConvertUtils.convertProperties(orderItemBO, OrderItem.class);
        orderItemService.updateById(orderItem);
        return ResponseEntity.success();
    }
}
