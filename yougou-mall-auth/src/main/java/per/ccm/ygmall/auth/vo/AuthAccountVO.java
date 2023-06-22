package per.ccm.ygmall.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.vo.BaseVO;

/**
 * 认证授权账号信息
 * */
@Getter
@Setter
@ToString
public class AuthAccountVO extends BaseVO {

    @Schema(description = "主键ID")
    private Long authAccountId;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "状态 0-正常 1-禁用")
    private Integer state;

    @Schema(description = "账号(11位)")
    private String account;

    @Schema(description = "电子邮箱")
    private String email;

    @Schema(description = "手机号")
    private String mp;

    @Schema(description = "角色")
    private String role;
}
