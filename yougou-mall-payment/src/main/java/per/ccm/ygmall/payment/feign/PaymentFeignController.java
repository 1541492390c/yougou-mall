package per.ccm.ygmall.payment.feign;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.feign.payment.bo.CouponUserBO;
import per.ccm.ygmall.feign.payment.feign.CouponUserFeign;
import per.ccm.ygmall.payment.service.CouponUserService;

@Hidden
@RestController
public class PaymentFeignController implements CouponUserFeign {

    @Autowired
    private CouponUserService couponUserService;

    @Override
    public ResponseEntity<CouponUserBO> useCoupon(Long couponUserId, String orderNo) throws Exception {
        CouponUserBO couponUserBO = couponUserService.useCoupon(couponUserId, orderNo);
        return ResponseEntity.success(couponUserBO);
    }
}
