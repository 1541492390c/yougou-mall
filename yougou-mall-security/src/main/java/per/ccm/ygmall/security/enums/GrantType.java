package per.ccm.ygmall.security.enums;

public enum GrantType {

    /**
     * 密码
     * */
    PASSWORD("password"),

    /**
     * 刷新令牌
     * */
    REFRESH_TOKEN("refresh_token");

    private final String value;

    GrantType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
