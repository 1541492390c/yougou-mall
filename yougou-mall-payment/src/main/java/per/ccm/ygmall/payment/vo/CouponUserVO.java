package per.ccm.ygmall.payment.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.vo.BaseVO;

import java.util.Date;

/**
 * 优惠券用户关联信息
 * */
@Getter
@Setter
@ToString
public class CouponUserVO extends BaseVO {

    @Schema(description = "主键ID")
    private Long couponUserId;

    @Schema(description = "优惠券ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long couponId;

    @Schema(description = "优惠券状态 0-已使用 1-已过期 2-待使用")
    private Integer state;

    @Schema(description = "领取时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date receiveTime;

    @Schema(description = "过期时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date expiredTime;

    @Schema(description = "优惠券信息")
    private CouponVO coupon;
}
