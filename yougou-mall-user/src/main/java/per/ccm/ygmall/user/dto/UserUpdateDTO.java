package per.ccm.ygmall.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 用户更新传输数据
 * */
@Getter
@Setter
@ToString
public class UserUpdateDTO {

    @Schema(name = "用户ID")
    private Long userId;

    @Schema(name = "状态 0-禁用 1-正常")
    private Integer state;

    @Schema(description = "性别 1-男 2-女")
    private Integer gender;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "电子邮箱")
    private String email;

    @Schema(description = "角色")
    private String role;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "生日")
    private Date birthday;
}
