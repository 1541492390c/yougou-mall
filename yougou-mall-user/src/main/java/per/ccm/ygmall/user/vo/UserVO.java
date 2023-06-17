package per.ccm.ygmall.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import per.ccm.ygmall.common.vo.BaseVO;

import java.util.Date;

/**
 * 用户信息
 * */
@Getter
@Setter
@ToString
public class UserVO extends BaseVO {

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "性别 0-未填写 1-男 2-女")
    private Integer gender;

    @Schema(description = "用户类型 0-管理员 1-普通用户")
    private Integer userType;

    @Schema(description = "状态 0-正常 1-禁用")
    private Integer state;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "生日")
    private Date birthday;
}
