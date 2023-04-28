package per.ccm.ygmall.user.entity;

import per.ccm.ygmall.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
public class User extends BaseEntity {

    /**
     * 主键ID
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    /**
     * 年龄
     * */
    @Column(name = "age")
    private Integer age;

    /**
     * 性别 0-未知 1-男 2-女
     * */
    @Column(name = "gender")
    private Integer gender;

    /**
     * 用户角色 0-管理员 1-普通用户
     * */
    @Column(name = "user_type")
    private Integer userType;

    /**
     * 状态 0-正常 1-禁用
     * */
    @Column(name = "state")
    private Integer state;

    /**
     * 用户名
     * */
    @Column(name = "username")
    private String username;

    /**
     * 头像地址
     * */
    @Column(name = "avatar")
    private String avatar;

    /**
     * 生日
     * */
    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "enabled")
    private Boolean enabled;

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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", age=" + age +
                ", gender=" + gender +
                ", userType=" + userType +
                ", state=" + state +
                ", username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                ", birthday=" + birthday +
                ", enabled=" + enabled +
                '}';
    }
}
