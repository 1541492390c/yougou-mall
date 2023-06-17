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

    @Schema(name = "skuID")
    private Long skuId;

    @Schema(name = "属性ID")
    private Long attrId;

    @Schema(name = "属性值ID")
    private Long attrValueId;
}
