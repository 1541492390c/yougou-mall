package per.ccm.ygmall.order.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.database.vo.PageVO;
import per.ccm.ygmall.order.entity.Order;
import per.ccm.ygmall.order.service.OrderService;
import per.ccm.ygmall.order.vo.OrderVO;

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
            @Parameter(name = "page_size", description = "页数")
    })
    public ResponseEntity<PageVO<OrderVO>> getOrderPages(
            @RequestParam(value = "page_num", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) throws Exception {
        Page<Order> page = new Page<>(pageNum, pageSize);
        PageVO<OrderVO> pageVO = orderService.getOrderPages(null, page);
        return ResponseEntity.success(pageVO);
    }
}
