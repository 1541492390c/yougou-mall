package per.ccm.ygmall.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.vo.BaseVO;

import java.util.List;

/**
 * 商品sku信息
 * */
@Getter
@Setter
@ToString
public class SkuVO extends BaseVO {

    @Schema(description = "主键ID")
    private Long skuId;

    @Schema(description = "商品ID")
    private Long productId;

    @Schema(description = "商品库存")
    private Integer skuStock;

    @Schema(description = "sku价格")
    private Double price;

    @Schema(description = "sku描述")
    private String description;

    @Schema(description = "sku规格")
    private List<SkuSpecsVO> skuSpecs;
}
