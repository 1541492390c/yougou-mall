package per.ccm.ygmall.payment.feign;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.common.basic.util.ConvertUtils;
import per.ccm.ygmall.feign.payment.bo.CouponUserBO;
import per.ccm.ygmall.feign.payment.bo.CouponUserLogBO;
import per.ccm.ygmall.feign.payment.feign.PaymentFeign;
import per.ccm.ygmall.payment.entity.CouponUserLog;
import per.ccm.ygmall.payment.service.CouponUserLogService;
import per.ccm.ygmall.payment.service.CouponUserService;

@Hidden
@RestController
public class PaymentFeignController implements PaymentFeign {

    @Autowired
    private CouponUserService couponUserService;

    @Autowired
    private CouponUserLogService couponUserLogService;

    @Override
    public ResponseEntity<CouponUserBO> getCouponUserById(Long couponId) throws Exception {
        CouponUserBO couponUserBO = couponUserService.getCouponUserBOById(couponId);
        return ResponseEntity.success(couponUserBO);
    }

    @Override
    public ResponseEntity<Void> saveCouponUserLog(CouponUserLogBO couponUserLogBO) {
        CouponUserLog couponUserLog = ConvertUtils.convertProperties(couponUserLogBO, CouponUserLog.class);
        couponUserLogService.save(couponUserLog);
        return ResponseEntity.success();
    }
}
