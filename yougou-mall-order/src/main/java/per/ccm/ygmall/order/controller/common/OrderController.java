package per.ccm.ygmall.order.controller.common;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.order.service.OrderService;

@RestController
@RequestMapping("/order")
@Tag(name = "订单接口", description = "订单接口")
public class OrderController {

    @Autowired
    private OrderService orderService;
}
