package per.ccm.ygmall.auth.entity;

import com.baomidou.mybatisplus.annotation.*;
import per.ccm.ygmall.database.entity.BaseEntity;

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
    private String email;

    /**
     * 手机号
     * */
    private String mp;

    /**
     * 角色
     * */
    private String role;

    /**
     * 是否启用
     * */
    @TableLogic(value = "1", delval = "0")
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
