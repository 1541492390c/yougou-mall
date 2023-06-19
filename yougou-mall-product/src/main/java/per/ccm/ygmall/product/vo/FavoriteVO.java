package per.ccm.ygmall.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.vo.BaseVO;

/**
 * 用户收藏信息
 * */
@Getter
@Setter
@ToString
public class FavoriteVO extends BaseVO {

    @Schema(name = "主键ID")
    private Long favoriteId;

    @Schema(name = "用户ID")
    private Long userId;

    @Schema(name = "商品ID")
    private Long productId;

    @Schema(name = "商品名称")
    private String productName;

    @Schema(name = "封面")
    private String cover;
}
