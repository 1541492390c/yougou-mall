package per.ccm.ygmall.payment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * 查询可用优惠券传输数据
 * */
@Setter
@Getter
@ToString
public class QueryAvailableCouponDTO {

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "总价")
    private BigDecimal totalAmount;

    @Schema(description = "分类节点列表")
    private List<String> categoryNodeList;
}
