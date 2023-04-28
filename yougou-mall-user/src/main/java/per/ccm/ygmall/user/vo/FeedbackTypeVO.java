package per.ccm.ygmall.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import per.ccm.ygmall.common.vo.BaseVO;

/**
 * 用户反馈类型信息
 * */
public class FeedbackTypeVO extends BaseVO {

    @Schema(description = "用户反馈类型ID")
    private Long feedbackTypeId;

    @Schema(description = "用户反馈类型名称")
    private String feedbackTypeName;

    public Long getFeedbackTypeId() {
        return feedbackTypeId;
    }

    public void setFeedbackTypeId(Long feedbackTypeId) {
        this.feedbackTypeId = feedbackTypeId;
    }

    public String getFeedbackTypeName() {
        return feedbackTypeName;
    }

    public void setFeedbackTypeName(String feedbackTypeName) {
        this.feedbackTypeName = feedbackTypeName;
    }

    @Override
    public String toString() {
        return "FeedbackTypeVO{" +
                "feedbackTypeId=" + feedbackTypeId +
                ", feedbackTypeName=" + feedbackTypeName +
                '}';
    }
}
