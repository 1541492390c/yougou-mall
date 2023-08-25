package per.ccm.ygmall.feign.payment.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 用户优惠券使用记录内部传输数据
 * */
@Setter
@Getter
@ToString
public class CouponUserLogBO {

    /**
     * 主键ID
     * */
    private Long couponUserLogId;

    /**
     * 优惠券ID
     * */
    private Long couponId;

    /**
     * 用户优惠券ID
     * */
    private Long couponUserId;

    /**
     * 折扣金额
     * */
    private BigDecimal discountAmount;

    /**
     * 订单号
     * */
    private String orderNo;

    /**
     * 是否实际支付
     * */
    private Boolean isPay;
}
