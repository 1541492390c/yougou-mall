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

    @Schema(name = "主键ID")
    private Long attrId;

    @Schema(name = "商品ID")
    private Long productId;

    @Schema(name = "商品属性名称")
    private String name;
}
