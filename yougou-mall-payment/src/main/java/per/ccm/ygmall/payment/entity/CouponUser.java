package per.ccm.ygmall.payment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.database.entity.BaseEntity;

import java.util.Date;

/**
 * 优惠券与用户关联表
 * */
@Getter
@Setter
@ToString
@TableName("coupon_user")
public class CouponUser extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.AUTO)
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
     * 优惠券状态 0-已使用 1-已过期 2-待使用
     * */
    private Integer state;

    /**
     * 领取时间
     * */
    private Date receiveTime;

    /**
     * 过期时间
     * */
    private Date expiredTime;
}
