package per.ccm.ygmall.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.basic.vo.BaseVO;

/**
 * 评分统计信息
 * */
@Getter
@Setter
@ToString
public class RateStatisticsVO extends BaseVO {

    @Schema(description = "平均分")
    private Double average;

    @Schema(description = "评分为1.0的数量")
    private Double oneCount;

    @Schema(description = "评分为2.0的数量")
    private Double twoCount;

    @Schema(description = "评分为3.0的数量")
    private Double threeCount;

    @Schema(description = "评分为4.0的数量")
    private Double fourCount;

    @Schema(description = "评分为5.0的数量")
    private Double fiveCount;
}
