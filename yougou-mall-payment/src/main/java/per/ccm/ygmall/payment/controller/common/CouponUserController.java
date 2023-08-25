package per.ccm.ygmall.payment.controller.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.payment.dto.QueryAvailableCouponDTO;
import per.ccm.ygmall.payment.service.CouponUserService;
import per.ccm.ygmall.payment.vo.CouponUserVO;
import per.ccm.ygmall.common.security.util.SecurityContextUtils;

import java.util.List;

@RestController
@RequestMapping("/payment/coupon_user")
@Tag(name = "优惠券用户关联接口", description = "优惠券用户关联接口")
public class CouponUserController {

    @Autowired
    private CouponUserService couponUserService;

    @GetMapping("/list")
    @Operation(summary = "获取用户优惠券信息", description = "获取用户优惠券信息")
    public ResponseEntity<List<CouponUserVO>> getCouponUserList() throws Exception {
        Long userId = SecurityContextUtils.getUserId();
        List<CouponUserVO> couponUserList = couponUserService.getCouponUserListByUserId(userId);
        return ResponseEntity.success(couponUserList);
    }

    @PostMapping("/available_coupon")
    @Operation(summary = "查询可用优惠券", description = "查询可用优惠券")
    public ResponseEntity<List<CouponUserVO>> queryAvailableCoupon(@RequestBody QueryAvailableCouponDTO queryAvailableCouponDTO) throws Exception {
        Long userId = SecurityContextUtils.getUserId();

        queryAvailableCouponDTO.setUserId(userId);
        List<CouponUserVO> couponUserList = couponUserService.getAvailableCoupon(queryAvailableCouponDTO);
        return ResponseEntity.success(couponUserList);
    }
}
