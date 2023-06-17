package per.ccm.ygmall.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.vo.BaseVO;

/**
 * 商品属性值信息
 * */
@Getter
@Setter
@ToString
public class AttrValueVO extends BaseVO {

    @Schema(name = "商品属性值ID")
    private Long attrValueId;

    @Schema(name = "商品属性ID")
    private Long attrId;

    @Schema(name = "商品属性值名称")
    private String name;
}
