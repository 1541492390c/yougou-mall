package per.ccm.ygmall.product.dto;

import per.ccm.ygmall.common.dto.BaseDTO;

/**
 * 商品属性值传数据
 * */
public class AttrValueDTO extends BaseDTO {

    private Long attrValueId;

    private Long attrId;

    private String name;

    public Long getAttrValueId() {
        return attrValueId;
    }

    public void setAttrValueId(Long attrValueId) {
        this.attrValueId = attrValueId;
    }

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AttrValueDTO{" +
                "attrValueId=" + attrValueId +
                ", attrId=" + attrId +
                ", name='" + name + '\'' +
                '}';
    }
}
