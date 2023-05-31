package per.ccm.ygmall.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 商品属性传输数据
 * */
public class AttrDTO {

    @Schema(name = "主键ID")
    private Long attrId;

    @Schema(name = "商品ID")
    private Long productId;

    @Schema(name = "商品属性名称")
    private String name;

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
                ", productId=" + productId +
                ", name='" + name + '\'' +
                '}';
    }
}
