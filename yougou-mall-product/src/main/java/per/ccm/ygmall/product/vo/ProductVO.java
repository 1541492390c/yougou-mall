package per.ccm.ygmall.product.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.basic.vo.BaseVO;

import java.math.BigDecimal;

/**
 * 商品信息
 * */
@Getter
@Setter
@ToString
public class ProductVO extends BaseVO {

    @Schema(description = "主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long productId;

    @Schema(description = "品牌ID")
    private Long brandId;

    @Schema(description = "商品状态 0-上架 1-已下架")
    private Integer state;

    @Schema(description = "折扣")
    private Integer discount;

    @Schema(description = "sku最低价")
    private BigDecimal price;

    @Schema(description = "sku最低折扣价")
    private BigDecimal discountPrice;

    @Schema(description = "分类节点")
    private String categoryNode;

    @Schema(description = "商品名称")
    private String name;

    @Schema(description = "商品封面")
    private String cover;

    @Schema(description = "商品图片列表")
    private String imgList;

    @Schema(description = "是否折扣")
    private Boolean isDiscount;

    @Schema(description = "是否推荐")
    private Boolean recommended;
}
