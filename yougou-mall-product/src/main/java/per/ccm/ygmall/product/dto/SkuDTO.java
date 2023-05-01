package per.ccm.ygmall.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import per.ccm.ygmall.common.dto.BaseDTO;

/**
 * sku传输数据
 * */
public class SkuDTO extends BaseDTO {

    @Schema(name = "主键ID")
    private Long skuId;

    @Schema(name = "spuID")
    private Long spuId;

    @Schema(name = "sku描述")
    private String desc;

    @Schema(name = "规格")
    private String specs;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    @Override
    public String toString() {
        return "SkuDTO{" +
                "skuId=" + skuId +
                ", spuId=" + spuId +
                ", desc='" + desc + '\'' +
                ", specs='" + specs + '\'' +
                '}';
    }
}
