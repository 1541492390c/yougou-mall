package per.ccm.ygmall.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import per.ccm.ygmall.common.vo.BaseVO;

/**
 * sku规格信息
 * */
public class SkuSpecsVO extends BaseVO {

    @Schema(name = "属性名称")
    private String attrName;

    @Schema(name = "属性值名称")
    private String attrValueName;

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrValueName() {
        return attrValueName;
    }

    public void setAttrValueName(String attrValueName) {
        this.attrValueName = attrValueName;
    }

    @Override
    public String toString() {
        return "SkuSpecsVO{" +
                "attrName='" + attrName + '\'' +
                ", attrValueName='" + attrValueName + '\'' +
                '}';
    }
}
