package per.ccm.ygmall.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import per.ccm.ygmall.common.entity.BaseEntity;

import java.util.Date;

@TableName("user")
public class User extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.ASSIGN_ID)
    private Long userId;

    /**
     * 性别 0-未知 1-男 2-女
     * */
    @TableField("gender")
    private Integer gender;

    /**
     * 用户角色 0-管理员 1-普通用户
     * */
    @TableField("user_type")
    private Integer userType;

    /**
     * 状态 0-禁用 1-正常
     * */
    @TableField("state")
    private Integer state;

    /**
     * 用户名
     * */
    @TableField("username")
    private String username;

    /**
     * 头像地址
     * */
    @TableField("avatar")
    private String avatar;

    /**
     * 生日
     * */
    @TableField("birthday")
    private Date birthday;

    @TableField("enabled")
    @TableLogic(value = "1", delval = "0")
    private Boolean enabled;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
