package per.ccm.ygmall.common.security.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.basic.vo.BaseVO;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class TokenVO extends BaseVO {

    @Schema(description = "认证token")
    private String accessToken;

    @Schema(description = "过期时间")
    private Long expiredIn;
}
