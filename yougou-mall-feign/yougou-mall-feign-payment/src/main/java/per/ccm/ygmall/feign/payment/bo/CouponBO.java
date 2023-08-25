package per.ccm.ygmall.feign.payment.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 优惠券内部传输数据
 * */
@Getter
@Setter
@ToString
public class CouponBO {

    /**
     * 主键ID
     * */
    private Long couponId;

    /**
     * 过期天数
     * */
    private Integer expired;

    /**
     * 使用金额
     * */
    private BigDecimal usedAmount;

    /**
     * 折扣金额
     * */
    private BigDecimal discountAmount;

    /**
     * 描述
     * */
    private String description;
}
