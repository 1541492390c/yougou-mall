package per.ccm.ygmall.platform.enums;

public enum BannerType {

    PC(1), MOBILE(2), MINI_APP(3);

    private final Integer value;

    BannerType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
