package per.ccm.ygmall.security.enums;

public enum UserType {

    /**
     * 管理员
     * */
    ADMIN("admin"),

    /**
     * 普通用户
     * */
    USER("user");

    private final String value;

    UserType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
