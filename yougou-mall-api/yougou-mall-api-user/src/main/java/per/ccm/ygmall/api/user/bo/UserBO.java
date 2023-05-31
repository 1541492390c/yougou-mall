package per.ccm.ygmall.api.user.bo;

public class UserBO {

    private Long userId;

    private String avatar;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "UserBO{" +
                "userId=" + userId +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
