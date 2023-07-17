package per.ccm.ygmall.user.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.vo.BaseVO;

/**
 * 用户反馈信息
 * */
@Getter
@Setter
@ToString
public class FeedbackVO extends BaseVO {

    @Schema(description = "主键ID")
    private Long feedbackId;

    @Schema(description = "用户ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    @Schema(description = "反馈类型ID")
    private Long feedbackTypeId;

    @Schema(description = "用户评分")
    private Double rate;

    @Schema(description = "反馈类型名称")
    private String feedbackTypeName;

    @Schema(description = "反馈内容")
    private String content;

    @Schema(description = "联系方式 (手机/电话/邮箱)")
    private String contactWay;
}
