package per.ccm.ygmall.feign.payment.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.basic.config.FeignUrlConfig;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.feign.payment.bo.CouponUserBO;

@FeignClient(value = "yougou-mall-payment", contextId = "coupon-user")
@RequestMapping(FeignUrlConfig.FEIGN_INNER_URL + "/payment/coupon_user")
public interface CouponUserFeign {
    /**
     * 使用优惠券,记录优惠券使用记录,并返回用户优惠券内部传输数据
     *
     * @param couponUserId 用户优惠券ID
     * @param orderNo 订单号
     * */
    @GetMapping("/use_coupon")
    ResponseEntity<CouponUserBO> useCoupon(@RequestParam("coupon_user_id") Long couponUserId, @RequestParam("order_no") String orderNo) throws Exception;
}
