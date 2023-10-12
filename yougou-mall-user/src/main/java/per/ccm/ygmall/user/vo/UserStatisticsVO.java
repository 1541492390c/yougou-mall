package per.ccm.ygmall.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.basic.vo.BaseVO;

@Getter
@Setter
@ToString
public class UserStatisticsVO extends BaseVO {

    @Schema(description = "用户总数")
    private Integer total;

    @Schema(description = "年份")
    private String year;

    @Schema(description = "月份")
    private String month;
}
