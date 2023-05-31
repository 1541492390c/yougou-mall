package per.ccm.ygmall.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 认证账号传输数据
 * */
public class AuthAccountDTO {

    @Schema(description = "主键ID")
    private Long authAccountId;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "电子邮箱")
    private String email;

    @Schema(description = "手机号")
    private String mp;

    @Schema(description = "角色")
    private String role;

    @Schema(description = "是否启用")
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
        return "AuthAccountDTO{" +
                "authAccountId=" + authAccountId +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", mp='" + mp + '\'' +
                ", role='" + role + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
