package per.ccm.ygmall.payment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CouponUserStateEnum {

    /**
     * 已过期
     * */
    EXPIRED(0, "已过期"),

    /**
     * 待使用
     * */
    WAIT_USE(1, "待使用"),

    /**
     * 已使用
     * */
    USED(2, "已使用");

    private final Integer value;

    private final String message;
}
