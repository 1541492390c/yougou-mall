package per.ccm.ygmall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import per.ccm.ygmall.database.entity.BaseEntity;

/**
 * 商品属性值
 * */
@TableName("attr_value")
public class AttrValue extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.AUTO)
    private Long attrValueId;

    /**
     * 属性ID
     * */
    private Long attrId;

    /**
     * 属性名称
     * */
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
        return "AttrValue{" +
                "attrValueId=" + attrValueId +
                ", attrId=" + attrId +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
