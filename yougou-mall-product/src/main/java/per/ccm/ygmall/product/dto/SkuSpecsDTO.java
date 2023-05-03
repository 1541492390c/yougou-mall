package per.ccm.ygmall.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import per.ccm.ygmall.common.dto.BaseDTO;

/**
 * sku规格传输数据
 * */
public class SkuSpecsDTO extends BaseDTO {

    @Schema(name = "skuID")
    private Long skuId;

    @Schema(name = "属性ID")
    private Long attrId;

    @Schema(name = "属性值ID")
    private Long attrValueId;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public Long getAttrValueId() {
        return attrValueId;
    }

    public void setAttrValueId(Long attrValueId) {
        this.attrValueId = attrValueId;
    }

    @Override
    public String toString() {
        return "SkuSpecsDTO{" +
                ", skuId=" + skuId +
                ", attrId=" + attrId +
                ", attrValueId=" + attrValueId +
                '}';
    }
}
