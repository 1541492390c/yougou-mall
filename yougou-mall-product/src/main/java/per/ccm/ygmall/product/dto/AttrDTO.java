package per.ccm.ygmall.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 商品属性传输数据
 * */
@Getter
@Setter
@ToString
public class AttrDTO {

    @Schema(description = "主键ID")
    private Long attrId;

    @Schema(description = "商品ID")
    private Long productId;

    @Schema(description = "商品属性名称")
    private String name;
}
