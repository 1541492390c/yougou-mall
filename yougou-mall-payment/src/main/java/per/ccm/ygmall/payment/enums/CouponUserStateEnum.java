package per.ccm.ygmall.payment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CouponUserStateEnum {

    /**
     * 已使用
     * */
    USED(0, "已使用"),

    /**
     * 待使用
     * */
    WAIT_USE(1, "待使用"),

    /**
     * 已过期
     * */
    EXPIRED(2, "已过期");

    private final Integer value;

    private final String message;
}
