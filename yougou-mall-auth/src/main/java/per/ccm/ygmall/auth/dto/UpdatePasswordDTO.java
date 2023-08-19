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

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "新密码")
    private String newPassword;
}
