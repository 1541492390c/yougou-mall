package per.ccm.ygmall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import per.ccm.ygmall.database.entity.BaseEntity;

/**
 * sku和属性值关联
 * */
@TableName("sku_specs")
public class SkuSpecs extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.AUTO)
    private Long skuSpecsId;

    /**
     * 商品ID
     * */
    private Long productId;

    /**
     * skuID
     * */
    private Long skuId;

    /**
     * 属性ID
     * */
    private Long attrId;

    /**
     * 属性值ID
     * */
    private Long attrValueId;

    /**
     * 属性名称
     * */
    private String attrName;

    /**
     * 属性值名称
     * */
    private String attrValueName;

    public Long getSkuSpecsId() {
        return skuSpecsId;
    }

    public void setSkuSpecsId(Long skuSpecsId) {
        this.skuSpecsId = skuSpecsId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
        return "SkuAttrValue{" +
                "skuSpecsId=" + skuSpecsId +
                ", productId=" + productId +
                ", skuId=" + skuId +
                ", attrId=" + attrId +
                ", attrValueId=" + attrValueId +
                ", attrName='" + attrName + '\'' +
                ", attrValueName='" + attrValueName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
