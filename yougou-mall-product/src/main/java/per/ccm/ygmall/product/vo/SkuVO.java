package per.ccm.ygmall.product.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.vo.BaseVO;

import java.math.BigDecimal;

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
    @JsonSerialize(using = ToStringSerializer.class)
    private Long productId;

    @Schema(description = "商品库存")
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
