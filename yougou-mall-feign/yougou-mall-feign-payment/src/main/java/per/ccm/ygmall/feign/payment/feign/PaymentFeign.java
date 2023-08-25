package per.ccm.ygmall.feign.payment.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.basic.config.FeignUrlConfig;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.feign.payment.bo.CouponUserBO;
import per.ccm.ygmall.feign.payment.bo.CouponUserLogBO;

@FeignClient("yougou-mall-payment")
@RequestMapping(FeignUrlConfig.FEIGN_INNER_URL + "/payment")
public interface PaymentFeign {
    /**
     * 根据优惠券ID获取优惠券内部传输数据
     *
     * @param couponUserId 用户优惠券ID
     * */
    @GetMapping("/coupon_user/get_by_id")
    ResponseEntity<CouponUserBO> getCouponUserById(@RequestParam("coupon_id") Long couponUserId) throws Exception;

    /**
     * 保存用户优惠券使用记录
     *
     * @param couponUserLogBO 用户优惠券使用记录内部传输数据
     * */
    @PostMapping("/coupon_user_log/save")
    ResponseEntity<Void> saveCouponUserLog(@RequestBody CouponUserLogBO couponUserLogBO) throws Exception;
}
