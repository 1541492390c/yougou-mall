package per.ccm.ygmall.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import per.ccm.ygmall.common.vo.BaseVO;

/**
 * 商品sku信息
 * */
public class SkuVO extends BaseVO {

    @Schema(name = "主键ID")
    private Long skuId;

    @Schema(name = "spuID")
    private Long spuId;

    @Schema(name = "商品库存")
    private Integer skuStock;

    @Schema(name = "sku价格")
    private Double price;

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

    @Override
    public String toString() {
        return "SkuVO{" +
                "skuId=" + skuId +
                ", spuId=" + spuId +
                ", skuStock=" + skuStock +
                ", price=" + price +
                '}';
    }
}
