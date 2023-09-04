package per.ccm.ygmall.payment.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.payment.dto.CouponDTO;
import per.ccm.ygmall.payment.service.CouponService;

@RestController("adminCouponController")
@RequestMapping("/admin/payment/coupon")
@PreAuthorize("hasAnyRole(@roleConfig.SUPER_ADMIN, @roleConfig.COMMON_ADMIN)")
@Tag(name = "优惠券接口(管理员)", description = "优惠券接口(管理员)")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PutMapping("/update")
    @Operation(summary = "更新优惠券", description = "更新优惠券")
    public ResponseEntity<Void> update(@RequestBody CouponDTO couponDTO) throws Exception {
        couponService.update(couponDTO);
        return ResponseEntity.success();
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole(@roleConfig.SUPER_ADMIN)")
    @Operation(summary = "删除优惠券", description = "传入优惠券ID删除优惠券")
    @Parameter(name = "coupon_id", description = "优惠券ID")
    public ResponseEntity<Void> delete(@RequestParam("coupon_id") Long couponId) {
        couponService.removeById(couponId);
        return ResponseEntity.success();
    }
}
