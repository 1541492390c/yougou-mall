package per.ccm.ygmall.auth.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.database.entity.BaseEntity;

/**
 * 认证账号
 * */
@Getter
@Setter
@ToString
@TableName("auth_account")
public class AuthAccount extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.ASSIGN_ID)
    private Long authAccountId;

    /**
     * 用户ID
     * */
    private Long userId;

    /**
     * 状态 0-禁用 1-正常
     * */
    private Integer state;

    /**
     * 用户名
     * */
    private String username;

    /**
     * 密码
     * */
    private String password;

    /**
     * 电子邮箱
     * */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String email;

    /**
     * 手机号
     * */
    private String mobile;

    /**
     * 角色
     * */
    private String role;

    /**
     * 是否启用
     * */
    private Boolean enabled;
}
