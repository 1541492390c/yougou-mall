package per.ccm.ygmall.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 评价传输数据
 * */
@Getter
@Setter
@ToString
public class CommentDTO {

    @Schema(description = "主键ID")
    private Long commentId;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "商品ID")
    private Long productId;

    @Schema(description = "订单ID")
    private Long orderId;

    @Schema(description = "订单项ID")
    private Long orderItemId;

    @Schema(description = "评分")
    private Double rate;

    @Schema(description = "图片列表")
    private String imgList;

    @Schema(description = "评价内容")
    private String content;
}
