package per.ccm.ygmall.search.vo.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.basic.vo.BaseVO;

@Setter
@Getter
@ToString
public class ESBaseVO extends BaseVO {

    @Schema(description = "内部标记")
    private String type;
}
