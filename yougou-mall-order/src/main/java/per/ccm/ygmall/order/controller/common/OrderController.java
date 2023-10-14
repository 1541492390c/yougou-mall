package per.ccm.ygmall.order.controller.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.common.basic.vo.PageVO;
import per.ccm.ygmall.order.dto.OrderDTO;
import per.ccm.ygmall.order.entity.Order;
import per.ccm.ygmall.order.enums.OrderStateEnum;
import per.ccm.ygmall.order.service.OrderService;
import per.ccm.ygmall.order.vo.OrderVO;
import per.ccm.ygmall.common.security.util.SecurityContextUtils;

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
    public ResponseEntity<PageVO<OrderVO>> getOrderPages(@RequestParam(value = "page_num", defaultValue = "1") Integer pageNum, @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) throws Exception {
        Long userId = SecurityContextUtils.getUserId();

        Page<Order> page = new Page<>(pageNum, pageSize);
        PageVO<OrderVO> pageInfo = orderService.getOrderPages(userId, page);
        return ResponseEntity.success(pageInfo);
    }

    @PutMapping("/confirm")
    @Operation(summary = "确认收货", description = "确认收货")
    @Parameter(name = "order_id", description = "订单ID", required = true)
    public ResponseEntity<Void> confirm(@RequestParam("order_id") Long orderId) throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        // 设置订单ID
        orderDTO.setOrderId(orderId);
        // 设置订单状态
        orderDTO.setState(OrderStateEnum.FINISHED.getValue());
        // 更新订单
        orderService.update(orderDTO);
        return ResponseEntity.success();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole(@roleConfig.SUPER_ADMIN)")
    @Operation(summary = "删除订单", description = "删除订单")
    public ResponseEntity<Void> delete(@PathVariable("id") Long orderId) throws Exception {
        orderService.delete(orderId);
        return ResponseEntity.success();
    }
}
