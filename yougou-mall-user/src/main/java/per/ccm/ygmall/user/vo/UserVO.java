package per.ccm.ygmall.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import per.ccm.ygmall.common.vo.BaseVO;

import java.util.Date;

/**
 * 用户信息
 * */
public class UserVO extends BaseVO {

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "年龄")
    private Integer age;

    @Schema(description = "性别 0-未填写 1-男 2-女")
    private Integer gender;

    @Schema(description = "用户类型 0-管理员 1-普通用户")
    private Integer userType;

    @Schema(description = "状态 0-正常 1-禁用")
    private Integer state;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "头像")
    private String avatar;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "userId=" + userId +
                ", age=" + age +
                ", gender=" + gender +
                ", userType=" + userType +
                ", state=" + state +
                ", username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
