package per.ccm.ygmall.payment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
public class CouponUserLog {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.ASSIGN_ID)
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
