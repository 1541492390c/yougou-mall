package per.ccm.ygmall.product.entity;

import org.hibernate.annotations.DynamicInsert;
import per.ccm.ygmall.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * 商品属性值
 * */
@Entity
@DynamicInsert
@Table(name = "attr_value")
public class AttrValue extends BaseEntity {

    /**
     * 主键ID
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attr_value_id")
    private Long attrValueId;

    /**
     * 属性ID
     * */
    @Column(name = "attr_id")
    private Long attrId;

    /**
     * 属性名称
     * */
    @Column(name = "name")
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
                '}';
    }
}
