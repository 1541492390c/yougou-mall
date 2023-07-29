package per.ccm.ygmall.payment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentTypeEnum {

    /**
     * 支付宝支付
     * */
    ALI_PAY(1, "支付宝支付");

    private final Integer value;

    private final String message;
}
