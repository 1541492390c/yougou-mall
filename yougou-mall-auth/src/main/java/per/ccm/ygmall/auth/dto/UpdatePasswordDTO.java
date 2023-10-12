package per.ccm.ygmall.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 更新密码传输数据
 * */
@Getter
@Setter
@ToString
public class UpdatePasswordDTO {

    @Schema(description = "修改密码类型 1-忘记密码 2-修改密码")
    private Integer updatePassType;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "原密码")
    private String password;

    @Schema(description = "新密码")
    private String newPassword;
}
