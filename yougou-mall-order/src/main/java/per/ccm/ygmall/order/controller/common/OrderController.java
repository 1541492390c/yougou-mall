package per.ccm.ygmall.order.controller.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.common.vo.PageVO;
import per.ccm.ygmall.order.dto.OrderDTO;
import per.ccm.ygmall.order.entity.Order;
import per.ccm.ygmall.order.service.OrderService;
import per.ccm.ygmall.order.vo.OrderVO;
import per.ccm.ygmall.security.util.SecurityContextUtils;

@RestController
@RequestMapping("/order")
@Tag(name = "订单接口", description = "订单接口")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/submit")
    @Operation(summary = "提交订单", description = "提交订单")
        public ResponseEntity<String> submit(@RequestBody OrderDTO orderDTO) throws Exception {
        Long userId = SecurityContextUtils.getUserId();

        orderDTO.setUserId(userId);
        Long orderId = orderService.save(orderDTO);
        // 需要将Long转为String,否则前端数据会丢失精度
        return ResponseEntity.success(String.valueOf(orderId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取订单信息", description = "根据ID获取订单信息")
    public ResponseEntity<OrderVO> getOrderById(@PathVariable("id") Long orderId) throws Exception {
        OrderVO orderVO = orderService.getOrderById(orderId);
        return ResponseEntity.success(orderVO);
    }

    @GetMapping("/pages")
    @Operation(summary = "获取订单分页信息", description = "获取订单分页信息")
    @Parameters({
            @Parameter(name = "page_num", description = "当前页"),
            @Parameter(name = "page_size", description = "页数")
    })
    public ResponseEntity<PageVO<OrderVO>> getOrderPages(
            @RequestParam(value = "page_num", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) throws Exception {
        Long userId = SecurityContextUtils.getUserId();

        Page<Order> page = new Page<>(pageNum, pageSize);
        PageVO<OrderVO> pageInfo = orderService.getOrderPages(userId, page);
        return ResponseEntity.success(pageInfo);
    }
}
