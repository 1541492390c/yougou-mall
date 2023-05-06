package per.ccm.ygmall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import per.ccm.ygmall.common.entity.BaseEntity;

/**
 * 商品属性
 * */
@TableName("attr")
public class Attr extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.AUTO)
    private Long attrId;

    /**
     * 商品ID
     * */
    @TableField("product_id")
    private Long productId;

    /**
     * 属性名称
     * */
    @TableField("name")
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
        return "Attr{" +
                "attrId=" + attrId +
                ", productId=" + productId +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
