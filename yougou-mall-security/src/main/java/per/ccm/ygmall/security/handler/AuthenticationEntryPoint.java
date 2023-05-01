package per.ccm.ygmall.security.handler;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.response.ResponseCode;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.common.util.JSONUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationEntryPoint extends OAuth2AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        String result;

        if (ObjectUtils.isEmpty(request.getHeader("Authorization"))) {
            result = JSONUtils.writeValueAsString(ResponseEntity.fail(ResponseCode.USER_ERROR_A00001));
            response.getWriter().print(result);
            return;
        }
        if (authException instanceof UsernameNotFoundException || authException instanceof BadCredentialsException) {
            result = JSONUtils.writeValueAsString(ResponseEntity.fail(ResponseCode.USER_ERROR_A00002));
            response.getWriter().print(result);
            return;
        }
        result = JSONUtils.writeValueAsString(ResponseEntity.fail(ResponseCode.USER_ERROR_A00003));
        response.getWriter().print(result);
    }
}