package per.ccm.ygmall.order.controller.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.order.dto.OrderDTO;
import per.ccm.ygmall.order.service.OrderService;
import per.ccm.ygmall.security.util.SecurityContextUtils;

@RestController
@RequestMapping("/order")
@Tag(name = "订单接口", description = "订单接口")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/submit")
    @Operation(summary = "提交订单", description = "提交订单")
    public ResponseEntity<Void> submit(@RequestBody OrderDTO orderDTO) throws Exception {
        Long userId = SecurityContextUtils.getUserId();

        orderDTO.setUserId(userId);
        orderService.save(orderDTO);
        return ResponseEntity.success();
    }
}
