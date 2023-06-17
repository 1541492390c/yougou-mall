package per.ccm.ygmall.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 商品属性值传数据
 * */
@Getter
@Setter
@ToString
public class AttrValueDTO {

    @Schema(name = "主键ID")
    private Long attrValueId;

    @Schema(name = "商品属性ID")
    private Long attrId;

    @Schema(name = "商品属性值名称")
    private String name;
}
