package per.ccm.ygmall.platform.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 用户反馈类型传输数据
 * */
public class FeedbackTypeDTO {

    @Schema(description = "用户反馈类型ID")
    private Long feedbackTypeId;

    @Schema(description = "用户反馈类型名称")
    private String name;

    public Long getFeedbackTypeId() {
        return feedbackTypeId;
    }

    public void setFeedbackTypeId(Long feedbackTypeId) {
        this.feedbackTypeId = feedbackTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FeedbackTypeDTO{" +
                "feedbackTypeId=" + feedbackTypeId +
                ", name='" + name + '\'' +
                '}';
    }
}
