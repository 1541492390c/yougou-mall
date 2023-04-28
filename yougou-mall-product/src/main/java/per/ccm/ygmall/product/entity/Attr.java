package per.ccm.ygmall.product.entity;

import org.hibernate.annotations.DynamicInsert;
import per.ccm.ygmall.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * 商品属性
 * */
@Entity
@DynamicInsert
@Table(name = "attr")
public class Attr extends BaseEntity {

    /**
     * 主键ID
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attr_id")
    private Long attrId;

    /**
     * spuID
     * */
    @Column(name = "spu_id")
    private Long spuId;

    /**
     * 属性名称
     * */
    @Column(name = "name")
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
        return "Attr{" +
                "attrId=" + attrId +
                ", spuId=" + spuId +
                ", name='" + name + '\'' +
                '}';
    }
}
