package per.ccm.ygmall.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * sku规格传输数据
 * */
@Getter
@Setter
@ToString
public class SkuSpecsDTO {

    @Schema(description = "skuID")
    private Long skuId;

    @Schema(description = "属性ID")
    private Long attrId;

    @Schema(description = "属性值ID")
    private Long attrValueId;
}
