package per.ccm.ygmall.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.database.entity.BaseEntity;

import java.util.Date;

/**
 * 用户反馈
 * */
@Getter
@Setter
@ToString
@TableName("feedback")
public class Feedback extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.AUTO)
    private Long feedbackId;

    /**
     * 用户ID
     * */
    private Long userId;

    /**
     * 反馈类型
     * */
    private Long feedbackTypeId;

    /**
     * 用户反馈类型名称
     * */
    private String feedbackTypeName;

    /**
     * 反馈内容
     * */
    private String content;

    /**
     * 联系方式 (手机/电话/邮箱)
     * */
    private String contactWay;

    /**
     * 图片列表
     * */
    private String imgList;

    /**
     * 反馈时间
     * */
    private Date feedbackTime;
}
