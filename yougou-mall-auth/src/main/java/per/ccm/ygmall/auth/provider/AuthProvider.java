package per.ccm.ygmall.auth.provider;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.auth.service.AuthAccountService;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCode;
import per.ccm.ygmall.common.util.JSONUtils;
import per.ccm.ygmall.security.config.RoleConfig;
import per.ccm.ygmall.security.enums.UserType;
import per.ccm.ygmall.security.principal.AuthPrincipal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private AuthAccountService authAccountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Map<String, String> params = JSONUtils.readValue(JSONUtils.writeValueAsString(authentication.getDetails()), new TypeReference<>() {
        });

        AuthPrincipal authPrincipal = (AuthPrincipal) authAccountService.loadUserByUsername(authentication.getName());

        List<? extends GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(authPrincipal.getAuthorities());
        String password = String.valueOf(authentication.getCredentials());
        String role = String.valueOf(authorities.get(0));

        // 密码错误
        if (!passwordEncoder.matches(password, authPrincipal.getPassword())) {
            throw new YougouException(ResponseCode.USER_ERROR_A00002);
        }
        //判断登录类型是否为管理员登录
        boolean isAdmin = ObjectUtils.nullSafeEquals(params.get("type"), UserType.ADMIN.value())
                && (ObjectUtils.nullSafeEquals(role, (RoleConfig.SUPER_ADMIN))
                || ObjectUtils.nullSafeEquals(role, (RoleConfig.COMMON_ADMIN)));
        //判断登录类型是否为用户登录
        boolean isUser = ObjectUtils.nullSafeEquals(params.get("type"), UserType.USER.value())
                && ObjectUtils.nullSafeEquals(role, RoleConfig.USER);

        if (!isAdmin && !isUser) {
            throw new YougouException(ResponseCode.USER_ERROR_A00005);
        }
        return new UsernamePasswordAuthenticationToken(authPrincipal, null, authPrincipal.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
