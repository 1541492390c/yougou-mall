package per.ccm.ygmall.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 订单收货地址传输数据
 * */
@Getter
@Setter
@ToString
public class OrderAddrDTO {

    @Schema(description = "订单ID")
    private Long orderId;

    @Schema(description = "收获地址ID")
    private Long addrId;

    @Schema(description = "收货人")
    private String consignee;

    @Schema(description = "联系电话")
    private String telephone;

    @Schema(description = "省份")
    private String province;

    @Schema(description = "城市")
    private String city;

    @Schema(description = "区、县")
    private String district;

    @Schema(description = "详细地址")
    private String detailedAddr;
}
