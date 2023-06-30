package per.ccm.ygmall.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 订单项传输数据
 * */
@Getter
@Setter
@ToString
public class OrderItemDTO {

    @Schema(description = "订单ID")
    private Long orderId;

    @Schema(description = "商品ID")
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
}
