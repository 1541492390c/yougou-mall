package per.ccm.ygmall.resource.enums;

public enum ResourceType {

    AVATAR("/avatar/");

    private final String value;

    ResourceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
