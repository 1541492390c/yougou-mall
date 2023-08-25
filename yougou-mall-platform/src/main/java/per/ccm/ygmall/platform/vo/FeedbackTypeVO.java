package per.ccm.ygmall.platform.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.basic.vo.BaseVO;

/**
 * 用户反馈类型信息
 * */
@Getter
@Setter
@ToString
public class FeedbackTypeVO extends BaseVO {

    @Schema(description = "用户反馈类型ID")
    private Long feedbackTypeId;

    @Schema(description = "用户反馈类型名称")
    private String name;
}
