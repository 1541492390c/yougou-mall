package per.ccm.ygmall.auth.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import per.ccm.ygmall.cache.cache.CacheNames;
import per.ccm.ygmall.security.enums.UserTypeEnum;
import per.ccm.ygmall.security.principal.AuthPrincipal;
import per.ccm.ygmall.security.util.TokenUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class LoginManager {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CacheManager cacheManager;

    /**
     * 用户名密码登录
     *
     * @param ipAddress ip地址
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param userTypeEnum 用户类型枚举
     * @return 认证token
     * */
    public String usernamePasswordLogin(String ipAddress, String username, String password, String code, UserTypeEnum userTypeEnum) {
        // 添加附加信息
        Map<String, String> params = new HashMap<>();
        params.put("ip_address", ipAddress);
        params.put("username", username);
        params.put("password", password);
        params.put("code", code);
        params.put("type", userTypeEnum.getName());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
        token.setDetails(params);
        // 验证
        Authentication authentication = authenticationManager.authenticate(token);
        AuthPrincipal authPrincipal = (AuthPrincipal) authentication.getPrincipal();
        // 将认证token存入redis中
        String accessToken = TokenUtils.createToken(authPrincipal.getAuthAccountId(), authPrincipal.getUserId(), authPrincipal.getUsername(),
                authPrincipal.getAuthorities().iterator().next().getAuthority());
        Objects.requireNonNull(cacheManager.getCache(CacheNames.ACCESS_TOKEN_NAME)).put(authPrincipal.getUserId(), accessToken);
        return accessToken;
    }
}
