package per.ccm.ygmall.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 用户传输数据
 * */
@Getter
@Setter
@ToString
public class UserRegisterDTO {

    @Schema(description = "性别 1-男 2-女")
    private Integer gender;

    @Schema(description = "用户类型")
    private Integer userType;

    @Schema(description = "状态 0-正常 1-禁用")
    private Integer state;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "电子邮箱")
    private String email;

    @Schema(description = "手机号")
    private String mp;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "角色")
    private String role;

    @Schema(description = "生日")
    private Date birthday;
}
