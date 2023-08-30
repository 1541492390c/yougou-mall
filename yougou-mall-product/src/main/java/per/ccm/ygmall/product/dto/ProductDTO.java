package per.ccm.ygmall.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 商品传输数据
 * */
@Getter
@Setter
@ToString
public class ProductDTO {

    @Schema(description = "主键ID")
    private Long productId;

    @Schema(description = "品牌ID")
    private Long brandId;

    @Schema(description = "商品状态 0-上架 1-已下架")
    private Integer state;

    @Schema(description = "折扣")
    private Integer discount;

    @Schema(description = "分类节点")
    private String categoryNode;

    @Schema(description = "商品名称")
    private String name;

    @Schema(description = "商品封面")
    private String cover;

    @Schema(description = "是否折扣")
    private Boolean isDiscount;

    @Schema(description = "是否推荐")
    private Boolean recommended;

    @Schema(description = "图片列表")
    private List<String> imgList;

    @Schema(description = "属性列表")
    private List<AttrDTO> attrList;

    @Schema(description = "sku列表")
    private List<SkuDTO> skuList;
}
