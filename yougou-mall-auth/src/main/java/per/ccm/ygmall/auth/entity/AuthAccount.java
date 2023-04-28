package per.ccm.ygmall.auth.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import per.ccm.ygmall.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 认证账号
 * */
@Entity
@Table(name = "auth_account")
public class AuthAccount extends BaseEntity {

    /**
     * 主键ID
     * */
    @Id
    @Column(name = "auth_account_id")
    private Long authAccountId;

    /**
     * 用户ID
     * */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 状态 0-正常 1-禁用
     * */
    @Column(name = "state")
    private Integer state;

    /**
     * 账号(11位)
     * */
    @Column(name = "account")
    private String account;

    /**
     * 用户名
     * */
    @Column(name = "username")
    private String username;

    /**
     * 密码
     * */
    @Column(name = "password")
    private String password;

    /**
     * 电子邮箱
     * */
    @Schema(name = "email")
    private String email;

    /**
     * 手机号
     * */
    @Column(name = "mp")
    private String mp;

    /**
     * 角色
     * */
    @Column(name = "role")
    private String role;

    /**
     * 是否启用
     * */
    @Column(name = "enabled")
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String authAccount) {
        this.account = authAccount;
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
                ", account='" + account + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", mp='" + mp + '\'' +
                ", role='" + role + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
