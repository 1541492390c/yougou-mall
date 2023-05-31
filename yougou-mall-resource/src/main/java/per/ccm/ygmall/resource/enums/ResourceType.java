package per.ccm.ygmall.resource.enums;

public enum ResourceType {

    /**
     * 头像资源
     * */
    AVATAR(1, "/avatar/");

    private final Integer value;

    private final String path;

    ResourceType(Integer value, String path) {
        this.value = value;
        this.path = path;
    }

    public Integer getValue() {
        return value;
    }

    public String getPath() {
        return path;
    }
}
