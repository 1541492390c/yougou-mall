package per.ccm.ygmall.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import per.ccm.ygmall.common.dto.BaseDTO;

/**
 * sku规格传输数据
 * */
public class SkuSpecsDTO extends BaseDTO {

    @Schema(name = "主键ID")
    private Long skuSpecsId;

    @Schema(name = "spuID")
    private Long spuId;

    @Schema(name = "skuID")
    private Long skuId;

    @Schema(name = "属性ID")
    private Long attrId;

    @Schema(name = "属性值ID")
    private Long attrValueId;

    public Long getSkuSpecsId() {
        return skuSpecsId;
    }

    public void setSkuSpecsId(Long skuSpecsId) {
        this.skuSpecsId = skuSpecsId;
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

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
                "skuSpecsId=" + skuSpecsId +
                ", spuId=" + spuId +
                ", skuId=" + skuId +
                ", attrId=" + attrId +
                ", attrValueId=" + attrValueId +
                '}';
    }
}
