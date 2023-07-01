package per.ccm.ygmall.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * 订单传输数据
 * */
@Getter
@Setter
@ToString
public class OrderDTO {

    @Schema(description = "订单ID")
    private Long orderId;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "订单状态 0-已取消 1-待付款 2-待发货 3-配送中 4-已完成")
    private Integer state;

    @Schema(description = "支付时间")
    private Date payTime;

    @Schema(description = "发货时间")
    private Date deliveryTime;

    @Schema(description = "完成时间")
    private Date finishTime;

    @Schema(description = "收货地址")
    private OrderAddrDTO orderAddrDTO;

    @Schema(description = "订单项列表")
    private List<OrderItemDTO> orderItemDTOList;
}
