package per.ccm.ygmall.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import per.ccm.ygmall.common.vo.BaseVO;

/**
 * 认证授权账号信息
 * */
public class AuthAccountVO extends BaseVO {

    @Schema(description = "主键ID")
    private Long authAccountId;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "状态 0-正常 1-禁用")
    private Integer state;

    @Schema(description = "账号(11位)")
    private String account;

    @Schema(name = "电子邮箱")
    private String email;

    @Schema(name = "手机号")
    private String mp;

    @Schema(name = "角色")
    private String role;

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

    public void setAccount(String account) {
        this.account = account;
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

    @Override
    public String toString() {
        return "AuthAccountVO{" +
                "authAccountId=" + authAccountId +
                ", userId=" + userId +
                ", state=" + state +
                ", account='" + account + '\'' +
                ", email='" + email + '\'' +
                ", mp='" + mp + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
