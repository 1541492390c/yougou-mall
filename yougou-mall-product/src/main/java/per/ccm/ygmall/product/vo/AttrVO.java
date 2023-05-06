package per.ccm.ygmall.product.vo;

import per.ccm.ygmall.common.vo.BaseVO;

import java.util.List;

/**
 * 商品属性信息
 * */
public class AttrVO extends BaseVO {

    /**
     * 商品属性ID
     * */
    private Long attrId;

    /**
     * 商品ID
     * */
    private Long productId;

    /**
     * 属性名称
     * */
    private String name;

    /**
     * 属性值列表
     * */
    private List<AttrValueVO> attrValueList;

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

    public List<AttrValueVO> getAttrValueList() {
        return attrValueList;
    }

    public void setAttrValueList(List<AttrValueVO> attrValueList) {
        this.attrValueList = attrValueList;
    }

    @Override
    public String toString() {
        return "AttrVO{" +
                "attrId=" + attrId +
                ", productId=" + productId +
                ", name='" + name + '\'' +
                ", attrValueList=" + attrValueList +
                '}';
    }
}
