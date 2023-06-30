package per.ccm.ygmall.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 订单传输数据
 * */
@Getter
@Setter
@ToString
public class OrderDTO {

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "收货地址")
    private OrderAddrDTO orderAddrDTO;

    @Schema(description = "订单项列表")
    private List<OrderItemDTO> orderItemDTOList;
}
