package per.ccm.ygmall.auth.provider;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.api.biz.feign.CaptchaFeign;
import per.ccm.ygmall.auth.service.AuthAccountService;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCodeEnum;
import per.ccm.ygmall.common.util.JSONUtils;
import per.ccm.ygmall.security.config.RoleConfig;
import per.ccm.ygmall.security.enums.UserTypeEnum;
import per.ccm.ygmall.security.principal.AuthPrincipal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class UsernamePasswordAuthProvider implements AuthenticationProvider {

    @Lazy
    @Autowired
    private AuthAccountService authAccountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CaptchaFeign captchaFeign;

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
            throw new YougouException(ResponseCodeEnum.USER_ERROR_A0002);
        }
        //判断登录类型是否为管理员登录
        boolean isAdmin = ObjectUtils.nullSafeEquals(params.get("type"), UserTypeEnum.ADMIN.getName())
                && (ObjectUtils.nullSafeEquals(role, RoleConfig.SUPER_ADMIN)
                || ObjectUtils.nullSafeEquals(role, RoleConfig.COMMON_ADMIN));
        //判断登录类型是否为用户登录
        boolean isUser = ObjectUtils.nullSafeEquals(params.get("type"), UserTypeEnum.USER.getName())
                && ObjectUtils.nullSafeEquals(role, RoleConfig.USER);

        if (!isAdmin && !isUser) {
            throw new YougouException(ResponseCodeEnum.USER_ERROR_A0005);
        }

        // 登录ip地址
        String ipAddress = params.get("ip_address");
        // 验证码
        String code = params.get("code");
        if (!captchaFeign.validate(ipAddress, code).responseSuccess()) {
            throw new YougouException(ResponseCodeEnum.SERVER_ERROR_00001);
        }
        Boolean validateResult = captchaFeign.validate(ipAddress, code).getData();
        // 判断验证码是否正确
        if (!validateResult) {
            throw new YougouException(ResponseCodeEnum.USER_ERROR_A0010);
        }
        // 验证码正确,移除验证码缓存
        captchaFeign.removeCaptchaCache(ipAddress);
        return new UsernamePasswordAuthenticationToken(authPrincipal, null, authPrincipal.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
