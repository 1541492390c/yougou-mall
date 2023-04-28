package per.ccm.ygmall.security.enums;

public enum GrantType {

    PASSWORD("password"),

    REFRESH_TOKEN("refresh_token");

    private final String value;


    GrantType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
