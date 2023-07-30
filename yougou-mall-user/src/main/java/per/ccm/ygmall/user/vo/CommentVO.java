package per.ccm.ygmall.user.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.vo.BaseVO;

import java.util.Date;

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
    @JsonSerialize(using = ToStringSerializer.class)
    private Long productId;

    @Schema(description = "订单ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderId;

    @Schema(description = "订单项ID")
    private Long orderItemId;

    @Schema(description = "评分")
    private Double rate;

    @Schema(description = "评价内容")
    private String content;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "用户头像")
    private String avatar;

    @Schema(description = "图片列表")
    private String imgList;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createTime;
}
