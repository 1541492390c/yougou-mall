package per.ccm.ygmall.product.entity;

import org.hibernate.annotations.DynamicInsert;
import per.ccm.ygmall.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * 商品Sku
 * */
@Entity
@DynamicInsert
@Table(name = "sku")
public class Sku extends BaseEntity {

    /**
     * 主键ID
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sku_id")
    private Long skuId;

    /**
     * spuID
     * */
    @Column(name = "spu_id")
    private Long spuId;

    /**
     * sku库存
     * */
    @Column(name = "sku_stock")
    private Integer skuStock;

    /**
     * sku价格
     * */
    @Column(name = "price")
    private Double price;

    /**
     * sku描述
     * */
    @Column(name = "desc")
    private String desc;

    /**
     * sku规格
     * */
    @Column(name = "specs")
    private String specs;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public Integer getSkuStock() {
        return skuStock;
    }

    public void setSkuStock(Integer skuStock) {
        this.skuStock = skuStock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    @Override
    public String toString() {
        return "Sku{" +
                "skuId=" + skuId +
                ", spuId=" + spuId +
                ", skuStock=" + skuStock +
                ", price=" + price +
                ", desc='" + desc + '\'' +
                ", specs='" + specs + '\'' +
                '}';
    }
}
