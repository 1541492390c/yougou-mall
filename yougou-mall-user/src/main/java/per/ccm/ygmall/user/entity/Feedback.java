package per.ccm.ygmall.user.entity;

import per.ccm.ygmall.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "feedback")
public class Feedback extends BaseEntity {

    /**
     * 主键ID
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Long feedbackId;

    /**
     * 用户ID
     * */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 反馈类型
     * */
    @Column(name = "feedback_type_id")
    private Long feedbackTypeId;

    /**
     * 评分
     * */
    @Column(name = "rate")
    private Double rate;

    /**
     * 反馈内容
     * */
    @Column(name = "content")
    private String content;

    /**
     * 联系方式 (手机/电话/邮箱)
     * */
    @Column(name = "contact_way")
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
        return "Feedback{" +
                "feedbackId=" + feedbackId +
                ", userId=" + userId +
                ", feedbackTypeId=" + feedbackTypeId +
                ", rate=" + rate +
                ", content='" + content + '\'' +
                ", contactWay='" + contactWay + '\'' +
                '}';
    }
}
