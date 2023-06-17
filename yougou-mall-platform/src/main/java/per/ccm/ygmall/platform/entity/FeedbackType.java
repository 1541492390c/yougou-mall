package per.ccm.ygmall.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.database.entity.BaseEntity;

/**
 * 用户反馈类型
 * */
@Getter
@Setter
@ToString
@TableName("feedback_type")
public class FeedbackType extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.AUTO)
    private Long feedbackTypeId;

    /**
     * 用户反馈类型名称
     * */
    private String name;
}
