package per.ccm.ygmall.security.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import per.ccm.ygmall.common.response.ResponseCodeEnum;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.common.util.JSONUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class PermissionDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        String result = JSONUtils.writeValueAsString(ResponseEntity.fail(ResponseCodeEnum.AUTH_ERROR_A0004));
        response.getWriter().print(result);
    }
}
