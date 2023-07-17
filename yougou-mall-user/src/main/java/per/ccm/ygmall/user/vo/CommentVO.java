package per.ccm.ygmall.user.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.vo.BaseVO;

/**
 * 评价信息
 * */
@Getter
@Setter
@ToString
public class CommentVO extends BaseVO {

    @Schema(description = "主键ID")
    private Long commentId;

    @Schema(description = "用户ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    @Schema(description = "商品ID")
    private Long productId;

    @Schema(description = "订单ID")
    private Long orderId;

    @Schema(description = "订单项ID")
    private Long orderItemId;

    @Schema(description = "评分")
    private Double rate;

    @Schema(description = "评价内容")
    private String text;

    @Schema(description = "用户名称")
    private String username;

    @Schema(description = "用户头像")
    private String avatar;
}
