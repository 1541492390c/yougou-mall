package per.ccm.ygmall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import per.ccm.ygmall.common.entity.BaseEntity;

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
     * spuID
     * */
    @TableField("spu_id")
    private Long spuId;

    /**
     * skuID
     * */
    @TableField("sku_id")
    private Long skuId;

    /**
     * 属性ID
     * */
    @TableField("attr_id")
    private Long attrId;

    /**
     * 属性值ID
     * */
    @TableField("attr_value_id")
    private Long attrValueId;

    /**
     * 属性名称
     * */
    @TableField("attr_name")
    private String attrName;

    /**
     * 属性值名称
     * */
    @TableField("attr_value_name")
    private String attrValueName;

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
                ", spuId=" + spuId +
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
