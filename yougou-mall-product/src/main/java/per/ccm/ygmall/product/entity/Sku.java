package per.ccm.ygmall.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import per.ccm.ygmall.common.entity.BaseEntity;

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
     * spuID
     * */
    @TableField("spu_id")
    private Long spuId;

    /**
     * sku库存
     * */
    @TableField("sku_stock")
    private Integer skuStock;

    /**
     * sku价格
     * */
    @TableField("price")
    private Double price;

    /**
     * sku描述
     * */
    @TableField("sku_desc")
    private String skuDesc;

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

    public String getSkuDesc() {
        return skuDesc;
    }

    public void setSkuDesc(String skuDesc) {
        this.skuDesc = skuDesc;
    }

    @Override
    public String toString() {
        return "Sku{" +
                "skuId=" + skuId +
                ", spuId=" + spuId +
                ", skuStock=" + skuStock +
                ", price=" + price +
                ", skuDesc='" + skuDesc + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
