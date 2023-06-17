package per.ccm.ygmall.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * sku传输数据
 * */
@Getter
@Setter
@ToString
public class SkuDTO {

    @Schema(name = "主键ID")
    private Long skuId;

    @Schema(name = "商品ID")
    private Long productId;

    @Schema(name = "sku价格")
    private Double price;

    @Schema(name = "sku描述")
    private String skuDesc;

    @Schema(name = "商品规格")
    private List<SkuSpecsDTO> skuSpecs;
}
