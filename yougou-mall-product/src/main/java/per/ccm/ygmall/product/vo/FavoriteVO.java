package per.ccm.ygmall.product.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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

    @Schema(description = "主键ID")
    private Long favoriteId;

    @Schema(description = "用户ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    @Schema(description = "商品ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long productId;

    @Schema(description = "商品名称")
    private String productName;

    @Schema(description = "封面")
    private String cover;
}
