package per.ccm.ygmall.security.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.response.ResponseCodeEnum;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.common.util.JSONUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        String result;

        if (ObjectUtils.isEmpty(request.getHeader("Authorization"))) {
            result = JSONUtils.writeValueAsString(ResponseEntity.fail(ResponseCodeEnum.AUTH_ERROR_A0001));
            response.getWriter().print(result);
            return;
        }
        if (authException instanceof UsernameNotFoundException || authException instanceof BadCredentialsException) {
            result = JSONUtils.writeValueAsString(ResponseEntity.fail(ResponseCodeEnum.AUTH_ERROR_A0002));
            response.getWriter().print(result);
            return;
        }
        result = JSONUtils.writeValueAsString(ResponseEntity.fail(ResponseCodeEnum.AUTH_ERROR_A0003));
        response.getWriter().print(result);
    }
}
