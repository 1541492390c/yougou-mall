package per.ccm.ygmall.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStateEnum {

    /**
     * 已取消
     * */
    CANCEL(0, "已取消"),

    /**
     * 待支付
     * */
    WAIT_PAY(1, "待支付"),

    /**
     * 待发货
     * */
    WAIT_DELIVERY(2, "待发货"),

    /**
     * 配送中
     * */
    DELIVERING(3, "配送中"),

    /**
     * 已完成
     * */
    FINISHED(4, "已完成");

    private final Integer value;

    private final String description;
}
