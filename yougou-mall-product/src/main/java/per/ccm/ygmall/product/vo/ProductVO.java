package per.ccm.ygmall.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.vo.BaseVO;

/**
 * 商品信息
 * */
@Getter
@Setter
@ToString
public class ProductVO extends BaseVO {

    @Schema(description = "主键ID")
    private Long productId;

    @Schema(description = "品牌ID")
    private Long brandId;

    @Schema(description = "商品状态 0-上架 1-已下架")
    private Integer state;

    @Schema(description = "sku最低价")
    private Double price;

    @Schema(description = "分类列表")
    private String categories;

    @Schema(description = "商品名称")
    private String name;

    @Schema(description = "商品封面")
    private String cover;

    @Schema(description = "商品图片列表")
    private String imgList;
}
