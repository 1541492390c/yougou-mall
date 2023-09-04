package per.ccm.ygmall.feign.auth.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AuthAccountBO {

    /**
     * 用户ID
     * */
    private Long userId;

    /**
     * 状态 0-正常 1-禁用
     * */
    private Integer state;

    /**
     * 用户名
     * */
    private String username;

    /**
     * 密码
     * */
    private String password;

    /**
     * 电子邮箱
     * */
    private String email;

    /**
     * 电话号
     * */
    private String mobile;
}
