package per.ccm.ygmall.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.basic.vo.BaseVO;

import java.math.BigDecimal;

/**
 * 订单统计数据信息
 * */
@Getter
@Setter
@ToString
public class OrderStatisticsVO extends BaseVO {

    @Schema(description = "订单总数")
    private Integer total;

    @Schema(description = "订单总金额")
    private BigDecimal totalAmount;

    @Schema(description = "年份")
    private String year;

    @Schema(description = "月份")
    private String month;
}
