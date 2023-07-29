package per.ccm.ygmall.api.order.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class OrderItemBO {

    /**
     * 主键ID
     * */
    private Long orderItemId;

    /**
     * 订单ID
     * */
    private Long orderId;

    /**
     * 商品ID
     * */
    private Long productId;

    /**
     * skuID
     * */
    private Long skuId;

    /**
     * 数量
     * */
    private Integer quantity;

    /**
     * 总金额
     * */
    private BigDecimal totalAmount;

    /**
     * 商品名称
     * */
    private String productName;
}
