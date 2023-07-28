package per.ccm.ygmall.payment.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.vo.BaseVO;

/**
 * 优惠券信息
 * */
@Getter
@Setter
@ToString
public class CouponVO extends BaseVO {

    @Schema(description = "主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long couponId;

    @Schema(description = "优惠券配额")
    private Integer quota;

    @Schema(description = "已领取数量")
    private Integer takeCount;

    @Schema(description = "过期天数")
    private Integer expired;

    @Schema(description = "使用金额")
    private Double usedAmount;

    @Schema(description = "折扣金额")
    private Double discountAmount;

    @Schema(description = "可用分类节点，为0则为通用优惠券")
    private String categoryNode;

    @Schema(description = "优惠券描述")
    private String description;
}
