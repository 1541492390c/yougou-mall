package per.ccm.ygmall.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * sku传输数据
 * */
public class SkuDTO {

    @Schema(name = "主键ID")
    private Long skuId;

    @Schema(name = "商品ID")
    private Long productId;

    @Schema(name = "sku价格")
    private Double price;

    @Schema(name = "sku描述")
    private String skuDesc;

    @Schema(name = "商品规格")
    private List<SkuSpecsDTO> skuSpecs;

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

    public void setSkuSpecs(List<SkuSpecsDTO> skuSpecs) {
        this.skuSpecs = skuSpecs;
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

    public List<SkuSpecsDTO> getSkuSpecs() {
        return skuSpecs;
    }

    public void setSkuSpecsList(List<SkuSpecsDTO> skuSpecs) {
        this.skuSpecs = skuSpecs;
    }

    @Override
    public String toString() {
        return "SkuDTO{" +
                "skuId=" + skuId +
                ", productId=" + productId +
                ", price=" + price +
                ", skuDesc='" + skuDesc + '\'' +
                ", skuSpecs=" + skuSpecs +
                '}';
    }
}
