package per.ccm.ygmall.security.principal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AuthPrincipal implements Serializable {

    /**
     * 认证授权ID
     * */
    private Long authAccountId;

    /**
     * 用户ID
     * */
    private Long userId;

    /**
     * 用户名
     * */
    private String username;

    /**
     * 登录ip地址
     * */
    private String ipAddress;

    /**
     * 授权范围
     * */
    private List<? extends GrantedAuthority> authorities;
}
