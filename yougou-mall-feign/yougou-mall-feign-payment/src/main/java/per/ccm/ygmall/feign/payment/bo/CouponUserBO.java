package per.ccm.ygmall.feign.payment.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 用户优惠券内部传输数据
 * */
@Getter
@Setter
@ToString
public class CouponUserBO {

    /**
     * 主键ID
     * */
    private Long couponUserId;

    /**
     * 优惠券ID
     * */
    private Long couponId;

    /**
     * 用户ID
     * */
    private Long userId;

    /**
     * 优惠券状态 0-已使用 1-待使用 2-已过期
     * */
    private Integer state;

    /**
     * 过期时间
     * */
    private Date expiredTime;

    /**
     * 优惠券
     * */
    private CouponBO couponBO;
}
