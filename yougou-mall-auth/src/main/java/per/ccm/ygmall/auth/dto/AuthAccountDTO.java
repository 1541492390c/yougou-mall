package per.ccm.ygmall.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 认证账号传输数据
 * */
@Getter
@Setter
@ToString
public class AuthAccountDTO {

    @Schema(description = "主键ID")
    private Long authAccountId;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "电子邮箱")
    private String email;

    @Schema(description = "手机号")
    private String mp;

    @Schema(description = "角色")
    private String role;

    @Schema(description = "是否启用")
    private Boolean enabled;
}
