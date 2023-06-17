package per.ccm.ygmall.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.vo.BaseVO;

/**
 * sku规格信息
 * */
@Getter
@Setter
@ToString
public class SkuSpecsVO extends BaseVO {

    @Schema(name = "属性名称")
    private String attrName;

    @Schema(name = "属性值名称")
    private String attrValueName;
}
