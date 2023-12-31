package per.ccm.ygmall.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * sku传输数据
 * */
@Getter
@Setter
@ToString
public class SkuDTO {

    @Schema(description = "主键ID")
    private Long skuId;

    @Schema(description = "商品ID")
    private Long productId;

    @Schema(description = "sku库存")
    private Integer skuStock;

    @Schema(description = "sku价格")
    private BigDecimal price;

    @Schema(description = "折扣价格")
    private BigDecimal discountPrice;

    @Schema(description = "sku描述")
    private String description;

    @Schema(description = "sku规格")
    private String specs;
}
