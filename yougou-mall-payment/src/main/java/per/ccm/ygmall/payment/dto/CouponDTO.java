package per.ccm.ygmall.payment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 优惠券传输数据
 * */
@Getter
@Setter
@ToString
public class CouponDTO {

    @Schema(name = "主键ID")
    private Long couponId;

    @Schema(name = "优惠券配额")
    private Integer quota;

    @Schema(name = "过期天数")
    private Integer expired;

    @Schema(name = "使用金额")
    private BigDecimal usedAmount;

    @Schema(name = "折扣金额")
    private BigDecimal discountAmount;

    @Schema(name = "可用分类节点，为0则为通用优惠券")
    private String categoryNode;

    @Schema(name = "优惠券描述")
    private String description;
}