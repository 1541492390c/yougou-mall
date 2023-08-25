package per.ccm.ygmall.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.database.entity.BaseEntity;

import java.util.Date;

/**
 * 用户
 * */
@Getter
@Setter
@ToString
@TableName("user")
public class User extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.ASSIGN_ID)
    private Long userId;

    /**
     * 性别 0-未知 1-男 2-女
     * */
    private Integer gender;

    /**
     * 状态 0-禁用 1-正常
     * */
    private Integer state;

    /**
     * 昵称
     * */
    private String nickname;

    /**
     * 头像地址
     * */
    private String avatar;

    /**
     * 生日
     * */
    private Date birthday;

    /**
     * 是否启用
     * */
    private Boolean enabled;
}
