package per.ccm.ygmall.security.enums;

public enum GrantType {

    /**
     * 密码
     * */
    PASSWORD("password");

    private final String value;

    GrantType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
