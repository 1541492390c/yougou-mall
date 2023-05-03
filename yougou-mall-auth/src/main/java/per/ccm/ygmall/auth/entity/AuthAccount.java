package per.ccm.ygmall.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import per.ccm.ygmall.common.entity.BaseEntity;

/**
 * 认证账号
 * */
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
    @TableField("user_id")
    private Long userId;

    /**
     * 状态 0-正常 1-禁用
     * */
    @TableField("state")
    private Integer state;

    /**
     * 用户名
     * */
    @TableField("username")
    private String username;

    /**
     * 密码
     * */
    @TableField("password")
    private String password;

    /**
     * 电子邮箱
     * */
    @TableField("email")
    private String email;

    /**
     * 手机号
     * */
    @TableField("mp")
    private String mp;

    /**
     * 角色
     * */
    @TableField("role")
    private String role;

    /**
     * 是否启用
     * */
    @TableField("enabled")
    private Boolean enabled;

    public Long getAuthAccountId() {
        return authAccountId;
    }

    public void setAuthAccountId(Long authAccountId) {
        this.authAccountId = authAccountId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMp() {
        return mp;
    }

    public void setMp(String mp) {
        this.mp = mp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "AuthAccount{" +
                "authAccountId=" + authAccountId +
                ", userId=" + userId +
                ", state=" + state +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", mp='" + mp + '\'' +
                ", role='" + role + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
