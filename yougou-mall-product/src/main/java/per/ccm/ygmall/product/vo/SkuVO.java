package per.ccm.ygmall.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import per.ccm.ygmall.common.vo.BaseVO;

import java.util.List;

/**
 * 商品sku信息
 * */
public class SkuVO extends BaseVO {

    @Schema(name = "主键ID")
    private Long skuId;

    @Schema(name = "商品ID")
    private Long productId;

    @Schema(name = "商品库存")
    private Integer skuStock;

    @Schema(name = "sku价格")
    private Double price;

    @Schema(name = "sku描述")
    private String skuDesc;

    @Schema(name = "sku规格")
    private List<SkuSpecsVO> skuSpecs;

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

    public String getSkuDesc() {
        return skuDesc;
    }

    public void setSkuDesc(String skuDesc) {
        this.skuDesc = skuDesc;
    }

    public List<SkuSpecsVO> getSkuSpecs() {
        return skuSpecs;
    }

    public void setSkuSpecs(List<SkuSpecsVO> skuSpecs) {
        this.skuSpecs = skuSpecs;
    }

    @Override
    public String toString() {
        return "SkuVO{" +
                "skuId=" + skuId +
                ", productId=" + productId +
                ", skuStock=" + skuStock +
                ", price=" + price +
                ", skuDesc=" + skuDesc +
                ", skuSpecs=" + skuSpecs +
                '}';
    }
}
