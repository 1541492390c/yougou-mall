package per.ccm.ygmall.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import per.ccm.ygmall.common.dto.BaseDTO;

/**
 * 商品属性传输数据
 * */
public class AttrDTO extends BaseDTO {

    @Schema(name = "商品属性ID")
    private Long attrId;

    @Schema(name = "spuID")
    private Long spuId;

    @Schema(name = "商品属性名称")
    private String name;

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AttrDTO{" +
                "attrId=" + attrId +
                ", spuId=" + spuId +
                ", name='" + name + '\'' +
                '}';
    }
}
