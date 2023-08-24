package per.ccm.ygmall.payment.controller.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.common.vo.PageVO;
import per.ccm.ygmall.payment.entity.Coupon;
import per.ccm.ygmall.payment.service.CouponService;
import per.ccm.ygmall.payment.vo.CouponUserVO;
import per.ccm.ygmall.payment.vo.CouponVO;
import per.ccm.ygmall.security.util.SecurityContextUtils;

@RestController
@RequestMapping("/payment/coupon")
@Tag(name = "优惠券接口", description = "优惠券接口")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping("/pages")
    @Operation(summary = "获取优惠券分页信息", description = "获取优惠券分页信息")
    @Parameters({
            @Parameter(name = "page_num", description = "当前页"),
            @Parameter(name = "page_size", description = "页数")
    })
    public ResponseEntity<PageVO<CouponVO>> getCouponPages(
            @RequestParam(value = "page_num", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) throws Exception {
        Page<Coupon> page = new Page<>(pageNum, pageSize);
        PageVO<CouponVO> pageVO = couponService.getCouponPages(page);
        return ResponseEntity.success(pageVO);
    }

    @PostMapping("/receive")
    @Operation(summary = "领取优惠券", description = "领取优惠券")
    @Parameter(name = "coupon_id", description = "优惠券ID")
    public ResponseEntity<CouponUserVO> receive(@RequestParam("coupon_id") Long couponId) throws Exception {
        Long userId = SecurityContextUtils.getUserId();
        CouponUserVO couponUserVO = couponService.receive(couponId, userId);
        return ResponseEntity.success(couponUserVO);
    }
}
