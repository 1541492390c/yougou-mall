package per.ccm.ygmall.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 用户反馈信息传输数据
 * */
public class FeedbackDTO {

    @Schema(description = "主键ID")
    private Long feedbackId;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "反馈类型")
    private Long feedbackTypeId;

    @Schema(description = "评分")
    private Double rate;

    @Schema(description = "反馈内容")
    private String content;

    @Schema(description = "联系方式 (手机/电话/邮箱)")
    private String contactWay;

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFeedbackTypeId() {
        return feedbackTypeId;
    }

    public void setFeedbackTypeId(Long feedbackTypeId) {
        this.feedbackTypeId = feedbackTypeId;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    @Override
    public String toString() {
        return "FeedbackDTO{" +
                "feedbackId=" + feedbackId +
                ", userId=" + userId +
                ", feedbackTypeId=" + feedbackTypeId +
                ", rate=" + rate +
                ", content='" + content + '\'' +
                ", contactWay='" + contactWay + '\'' +
                '}';
    }
}
