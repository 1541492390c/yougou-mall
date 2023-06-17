package per.ccm.ygmall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import per.ccm.ygmall.database.entity.BaseEntity;

/**
 * 商品Sku
 * */
@TableName("sku")
public class Sku extends BaseEntity {

    /**
     * 主键ID
     * */
    @TableId(type = IdType.AUTO)
    private Long skuId;

    /**
     * 商品ID
     * */
    private Long productId;

    /**
     * sku库存
     * */
    private Integer skuStock;

    /**
     * sku价格
     * */
    private Double price;

    /**
     * sku描述
     * */
    private String description;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Sku{" +
                "skuId=" + skuId +
                ", productId=" + productId +
                ", skuStock=" + skuStock +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
