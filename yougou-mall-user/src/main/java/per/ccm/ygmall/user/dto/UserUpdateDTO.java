package per.ccm.ygmall.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import per.ccm.ygmall.common.dto.BaseDTO;

import java.util.Date;

/**
 * 用户更新传输数据
 * */
public class UserUpdateDTO extends BaseDTO {

    @Schema(name = "用户ID")
    private Long userId;

    @Schema(description = "年龄")
    private Integer age;

    @Schema(description = "性别 1-男 2-女")
    private Integer gender;

    @Schema(description = "电子邮箱")
    private String email;

    @Schema(description = "角色")
    private String role;

    @Schema(description = "生日")
    private Date birthday;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return "UserUpdateDTO{" +
                "userId=" + userId +
                ", age=" + age +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
