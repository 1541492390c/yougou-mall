package per.ccm.ygmall.platform.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户反馈类型传输数据
 * */
@Getter
@Setter
@ToString
public class FeedbackTypeDTO {

    @Schema(description = "用户反馈类型ID")
    private Long feedbackTypeId;

    @Schema(description = "用户反馈类型名称")
    private String name;
}
