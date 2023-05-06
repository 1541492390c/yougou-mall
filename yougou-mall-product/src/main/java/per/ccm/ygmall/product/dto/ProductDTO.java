package per.ccm.ygmall.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import per.ccm.ygmall.common.dto.BaseDTO;

/**
 * 商品传输数据
 * */
public class ProductDTO extends BaseDTO {

    @Schema(name = "主键ID")
    private Long productId;

    @Schema(name = "品牌ID")
    private Long brandId;

    @Schema(name = "商品状态 0-上架 1-已下架")
    private Integer state;

    @Schema(name = "分类列表")
    private String categories;

    @Schema(name = "商品名称")
    private String name;

    @Schema(name = "商品封面")
    private String cover;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productId=" + productId +
                ", brandId=" + brandId +
                ", state=" + state +
                ", categories='" + categories + '\'' +
                ", name='" + name + '\'' +
                ", cover='" + cover + '\'' +
                '}';
    }
}
