package per.ccm.ygmall.user.entity;

import per.ccm.ygmall.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "feedback_type")
public class FeedbackType extends BaseEntity {

    /**
     * 主键ID
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_type_id")
    private Long feedbackTypeId;

    /**
     * 用户反馈类型名称
     * */
    @Column(name = "feedback_type_name")
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
        return "FeedbackType{" +
                "feedbackTypeId=" + feedbackTypeId +
                ", feedbackTypeName='" + feedbackTypeName + '\'' +
                '}';
    }
}
