package per.ccm.ygmall.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import per.ccm.ygmall.common.dto.BaseDTO;

import java.util.Date;

/**
 * 用户传输数据
 * */
public class UserRegisterDTO extends BaseDTO {

    @Schema(description = "年龄")
    private Integer age;

    @Schema(description = "性别 1-男 2-女")
    private Integer gender;

    @Schema(description = "用户类型")
    private Integer userType;

    @Schema(description = "状态 0-正常 1-禁用")
    private Integer state;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "电子邮箱")
    private String email;

    @Schema(description = "手机号")
    private String mp;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "角色")
    private String role;

    @Schema(description = "生日")
    private Date birthday;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                ", age=" + age +
                ", gender=" + gender +
                ", userType=" + userType +
                ", state=" + state +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", mp='" + mp + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
