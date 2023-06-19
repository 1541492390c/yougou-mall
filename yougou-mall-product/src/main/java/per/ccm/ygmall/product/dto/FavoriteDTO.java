package per.ccm.ygmall.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户收藏传输数据
 * */
@Getter
@Setter
@ToString
public class FavoriteDTO {

    @Schema(name = "主键ID")
    private Long favoriteId;

    @Schema(name = "用户ID")
    private Long userId;

    @Schema(name = "商品ID")
    private Long productId;
}
