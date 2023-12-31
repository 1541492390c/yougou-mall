package per.ccm.ygmall.feign.order.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
public class OrderBO {

    /**
     * 主键ID
     * */
    private Long orderId;

    /**
     * 用户ID
     * */
    private Long userId;

    /**
     * 用户优惠券ID
     * */
    private Long couponUserId;

    /**
     * 总金额
     * */
    private BigDecimal totalAmount;

    /**
     * 实付金额
     * */
    private BigDecimal payAmount;

    /**
     * 订单号
     * */
    private String orderNo;

    /**
     * 订单项内部传输数据列表
     * */
    private List<OrderItemBO> orderItemBOList;
}
