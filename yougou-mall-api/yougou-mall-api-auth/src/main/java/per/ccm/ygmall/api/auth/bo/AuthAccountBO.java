package per.ccm.ygmall.api.auth.bo;

public class AuthAccountBO {

    /**
     * 用户ID
     * */
    private Long userId;

    /**
     * 状态 0-正常 1-禁用
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
     * 电话号
     * */
    private String mp;

    /**
     * 角色
     * */
    private String role;

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

    @Override
    public String toString() {
        return "AuthAccountBO{" +
                "userId=" + userId +
                ", state=" + state +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", mp='" + mp + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
