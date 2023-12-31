package per.ccm.ygmall.order.controller.admin;

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
import per.ccm.ygmall.order.service.OrderService;
import per.ccm.ygmall.order.vo.OrderStatisticsVO;
import per.ccm.ygmall.order.vo.OrderVO;

import java.util.List;

@RestController("adminOrderController")
@RequestMapping("/admin/order")
@PreAuthorize("hasAnyRole(@roleConfig.SUPER_ADMIN, @roleConfig.COMMON_ADMIN)")
@Tag(name = "订单接口(管理员)", description = "订单接口(管理员)")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/pages")
    @Operation(summary = "获取订单分页信息", description = "获取订单分页信息")
    @Parameters({
            @Parameter(name = "page_num", description = "当前页"),
            @Parameter(name = "page_size", description = "页数"),
            @Parameter(name = "state", description = "订单状态 0-已取消 1-待付款 2-待发货 3-配送中 4-已完成"),
            @Parameter(name = "order_no", description = "订单号")
    })
    public ResponseEntity<PageVO<OrderVO>> getOrderPages(
            @RequestParam(value = "page_num", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "state", required = false) Integer state,
            @RequestParam(value = "order_no", required = false) String orderNo) throws Exception {
        Page<Order> page = new Page<>(pageNum, pageSize);
        PageVO<OrderVO> pageVO = orderService.getOrderPages(state, orderNo, page);
        return ResponseEntity.success(pageVO);
    }

    @GetMapping("/count")
    @Operation(summary = "统计订单数量", description = "统计订单数量")
    public ResponseEntity<Long> getOrderCount() {
        return ResponseEntity.success(orderService.count());
    }

    @GetMapping("/statistics")
    @Operation(summary = "获取订单统计数据信息", description = "获取订单统计数据信息")
    public ResponseEntity<List<OrderStatisticsVO>> getOrderStatics() throws Exception {
        return ResponseEntity.success(orderService.getOrderStatistics());
    }

    @PutMapping("/delivery")
    @Operation(summary = "订单发货", description = "订单发货,将订单状态改为发货状态")
    @Parameters({
            @Parameter(name = "order_id", description = "订单ID", required = true),
            @Parameter(name = "state", description = "订单状态 0-已取消 1-待付款 2-待发货 3-配送中 4-已完成", required = true)
    })
    public ResponseEntity<Void> delivery(@RequestParam("order_id") Long orderId, @RequestParam("state") Integer state) throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        // 设置订单ID
        orderDTO.setOrderId(orderId);
        // 设置订单状态
        orderDTO.setState(state);
        orderService.update(orderDTO);
        return ResponseEntity.success();
    }
}
