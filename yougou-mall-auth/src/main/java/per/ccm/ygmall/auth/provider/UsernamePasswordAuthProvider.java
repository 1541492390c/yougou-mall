package per.ccm.ygmall.auth.provider;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.api.extra.feign.BizFeign;
import per.ccm.ygmall.auth.entity.AuthAccount;
import per.ccm.ygmall.auth.service.AuthAccountService;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCodeEnum;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.common.util.JSONUtils;
import per.ccm.ygmall.security.config.RoleConfig;
import per.ccm.ygmall.security.enums.UserTypeEnum;
import per.ccm.ygmall.security.principal.AuthPrincipal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class UsernamePasswordAuthProvider implements AuthenticationProvider {

    @Autowired
    private AuthAccountService authAccountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BizFeign bizFeign;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Map<String, String> params = JSONUtils.readValue(JSONUtils.writeValueAsString(authentication.getDetails()), new TypeReference<>() {
        });

        // 根据用户名查询用户认证信息
        LambdaQueryWrapper<AuthAccount> queryWrapper = new LambdaQueryWrapper<>();
        AuthAccount authAccount = authAccountService.getOne(queryWrapper.eq(AuthAccount::getUsername, authentication.getPrincipal()));

        if (ObjectUtils.isEmpty(authAccount)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        // 设置用户角色
        String role = "ROLE_" + authAccount.getRole();
        // 获取输入的密码
        String password = String.valueOf(authentication.getCredentials());
        // 密码错误
        if (!passwordEncoder.matches(password, authAccount.getPassword())) {
            throw new YougouException(ResponseCodeEnum.AUTH_ERROR_A0002);
        }
        //判断登录类型是否为管理员登录
        boolean isAdmin = ObjectUtils.nullSafeEquals(params.get("type"), UserTypeEnum.ADMIN.getName())
                && (ObjectUtils.nullSafeEquals(role, RoleConfig.SUPER_ADMIN)
                || ObjectUtils.nullSafeEquals(role, RoleConfig.COMMON_ADMIN));
        //判断登录类型是否为用户登录
        boolean isUser = ObjectUtils.nullSafeEquals(params.get("type"), UserTypeEnum.USER.getName())
                && ObjectUtils.nullSafeEquals(role, RoleConfig.USER);

        // 登录类型错误
        if (!isAdmin && !isUser) {
            throw new YougouException(ResponseCodeEnum.AUTH_ERROR_A0005);
        }
        // 登录ip地址
        String ipAddress = params.get("ip_address");
        // 验证码
        String code = params.get("code");
        // 获取响应结果
        ResponseEntity<Boolean> response = bizFeign.validateCaptcha(ipAddress, code);
        if (!response.responseSuccess()) {
            throw new YougouException(ResponseCodeEnum.SERVER_ERROR_00001);
        }
        // 判断验证码是否正确
        if (!response.getData()) {
            throw new YougouException(ResponseCodeEnum.AUTH_ERROR_A0010);
        }
        // 添加授权范围
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(Collections.singleton(new SimpleGrantedAuthority(role)));
        // 用户认证信息
        AuthPrincipal authPrincipal = new AuthPrincipal(authAccount.getAuthAccountId(), authAccount.getUserId(), authAccount.getUsername(), ipAddress, authorities);
        return new UsernamePasswordAuthenticationToken(authPrincipal, null, authPrincipal.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
