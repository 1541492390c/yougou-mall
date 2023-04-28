package per.ccm.ygmall.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import per.ccm.ygmall.common.vo.BaseVO;

/**
 * 商品属性值信息
 * */
public class AttrValueVO extends BaseVO {

    @Schema(name = "商品属性值ID")
    private Long attrValueId;

    @Schema(name = "商品属性ID")
    private Long attrId;

    @Schema(name = "商品属性值名称")
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
        return "AttrValueVO{" +
                "attrValueId=" + attrValueId +
                ", attrId=" + attrId +
                ", name='" + name + '\'' +
                '}';
    }
}
