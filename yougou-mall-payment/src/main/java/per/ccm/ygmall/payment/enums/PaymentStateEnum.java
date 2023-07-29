package per.ccm.ygmall.payment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentStateEnum {

    /**
     * 支付成功
     * */
    SUCCESS(1, "支付成功"),

    /**
     * 支付失败
     * */
    FAIL(0, "支付失败");

    private final Integer value;

    private final String message;
}
