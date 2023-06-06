package per.ccm.ygmall.security.enums;

public enum UserType {

    /**
     * 管理员
     * */
    ADMIN(0, "admin"),

    /**
     * 普通用户
     * */
    USER(1, "user");

    private final Integer value;

    private final String name;

    UserType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
