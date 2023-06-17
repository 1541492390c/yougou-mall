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

    @Schema(name = "主键ID")
    private Long productId;

    @Schema(name = "品牌ID")
    private Long brandId;

    @Schema(name = "商品状态 0-上架 1-已下架")
    private Integer state;

    @Schema(name = "sku最低价")
    private Double price;

    @Schema(name = "分类列表")
    private String categories;

    @Schema(name = "商品名称")
    private String name;

    @Schema(name = "商品封面")
    private String cover;

    @Schema(name = "商品图片列表")
    private String imgList;
}
