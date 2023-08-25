package per.ccm.ygmall.user.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.basic.vo.BaseVO;

/**
 * 收货地址信息
 * */
@Getter
@Setter
@ToString
public class AddrVO extends BaseVO {

    @Schema(description = "主键ID")
    private Long addrId;

    @Schema(description = "用户ID")
    @JsonSerialize(using = ToStringSerializer.class)
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

    @Schema(description = "是否默认地址")
    private Boolean isDefault;
}
