package per.ccm.ygmall.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 收货地址传输数据
 * */
@Getter
@Setter
@ToString
public class AddrDTO {

    @Schema(description = "主键ID")
    private Long addrId;

    @Schema(description = "用户ID")
    private Long userId;

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
