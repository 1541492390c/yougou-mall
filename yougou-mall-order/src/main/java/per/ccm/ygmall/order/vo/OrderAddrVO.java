package per.ccm.ygmall.order.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.vo.BaseVO;

/**
 * 订单收获地址信息
 * */
@Getter
@Setter
@ToString
public class OrderAddrVO extends BaseVO {

    @Schema(description = "主键ID")
    private Long orderAddrId;

    @Schema(description = "订单ID")
    @JsonSerialize(using = ToStringSerializer.class)
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
