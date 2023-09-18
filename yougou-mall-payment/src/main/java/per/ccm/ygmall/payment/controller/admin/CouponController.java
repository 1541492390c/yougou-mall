package per.ccm.ygmall.payment.controller.admin;

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
import per.ccm.ygmall.payment.dto.CouponDTO;
import per.ccm.ygmall.payment.entity.Coupon;
import per.ccm.ygmall.payment.service.CouponService;
import per.ccm.ygmall.payment.vo.CouponVO;

@RestController("adminCouponController")
@RequestMapping("/admin/payment/coupon")
@PreAuthorize("hasAnyRole(@roleConfig.SUPER_ADMIN, @roleConfig.COMMON_ADMIN)")
@Tag(name = "优惠券接口(管理员)", description = "优惠券接口(管理员)")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/save")
    @Operation(summary = "保存优惠券", description = "保存优惠券")
    public ResponseEntity<Void> save(@RequestBody CouponDTO couponDTO) throws Exception {
        couponService.save(couponDTO);
        return ResponseEntity.success();
    }

    @GetMapping("/pages")
    @Operation(summary = "获取优惠券分页信息", description = "获取优惠券分页信息")
    @Parameters({
            @Parameter(name = "page_num", description = "当前页"),
            @Parameter(name = "page_size", description = "页数"),
            @Parameter(name = "quota", description = "配额"),
            @Parameter(name = "expired", description = "过期时间"),
            @Parameter(name = "category_node", description = "分类节点")
    })
    public ResponseEntity<PageVO<CouponVO>> getCouponPages(
            @RequestParam(value = "page_num", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "quota", required = false) Integer quota,
            @RequestParam(value = "expired", required = false) Integer expired,
            @RequestParam(value = "category_node", required = false) String categoryNode) throws Exception {
        Page<Coupon> page = new Page<>(pageNum, pageSize);
        PageVO<CouponVO> pageVO = couponService.getCouponPages(quota, expired, categoryNode, page);
        return ResponseEntity.success(pageVO);
    }

    @PutMapping("/update")
    @Operation(summary = "更新优惠券", description = "更新优惠券")
    public ResponseEntity<Void> update(@RequestBody CouponDTO couponDTO) throws Exception {
        couponService.update(couponDTO);
        return ResponseEntity.success();
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole(@roleConfig.SUPER_ADMIN)")
    @Operation(summary = "删除优惠券", description = "传入优惠券ID删除优惠券")
    @Parameter(name = "coupon_id", description = "优惠券ID", required = true)
    public ResponseEntity<Void> delete(@RequestParam("coupon_id") Long couponId) {
        couponService.removeById(couponId);
        return ResponseEntity.success();
    }
}
