package per.ccm.ygmall.payment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.database.entity.BaseEntity;

/**
 * 优惠券
 * */
@Getter
@Setter
@ToString
@TableName("coupon")
public class Coupon extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.ASSIGN_ID)
    private Long couponId;

    /**
     * 优惠券配额
     * */
    private Integer quota;

    /**
     * 已领取数量
     * */
    private Integer takeCount;

    /**
     * 过期天数
     * */
    private Integer expired;

    /**
     * 使用金额
     * */
    private Double usedAmount;

    /**
     * 折扣金额
     * */
    private Double discountAmount;

    /**
     * 可用分类节点，为0则为通用优惠券
     * */
    private String categoryNode;

    /**
     * 优惠券描述
     * */
    private String description;
}
