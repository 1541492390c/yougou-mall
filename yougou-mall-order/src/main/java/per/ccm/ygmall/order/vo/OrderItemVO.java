package per.ccm.ygmall.order.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.vo.BaseVO;

/**
 * 订单项信息
 * */
@Getter
@Setter
@ToString
public class OrderItemVO extends BaseVO {

    @Schema(description = "主键ID")
    private Long orderItemId;

    @Schema(description = "订单ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orderId;

    @Schema(description = "商品ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long productId;

    @Schema(description = "SkuID")
    private Long skuId;

    @Schema(description = "数量")
    private Integer quantity;

    @Schema(description = "总金额")
    private Double totalAmount;

    @Schema(description = "商品名称")
    private String productName;

    @Schema(description = "商品图片")
    private String img;

    @Schema(description = "商品规格")
    private String specs;

    @Schema(description = "是否评价")
    private Boolean isComment;
}
