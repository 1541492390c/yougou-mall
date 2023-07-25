package per.ccm.ygmall.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户反馈信息传输数据
 * */
@Getter
@Setter
@ToString
public class FeedbackDTO {

    @Schema(description = "主键ID")
    private Long feedbackId;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "反馈类型")
    private Long feedbackTypeId;

    @Schema(description = "用户反馈类型名称")
    private String feedbackTypeName;

    @Schema(description = "反馈内容")
    private String content;

    @Schema(description = "联系方式 (手机/电话/邮箱)")
    private String contactWay;

    @Schema(description = "图片列表")
    private String imgList;
}
