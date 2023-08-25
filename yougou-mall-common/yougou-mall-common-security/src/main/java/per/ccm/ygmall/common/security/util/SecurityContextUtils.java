package per.ccm.ygmall.common.security.util;

import org.springframework.security.core.context.SecurityContextHolder;
import per.ccm.ygmall.common.security.principal.AuthPrincipal;

public class SecurityContextUtils {

    public static Long getUserId() {
        return ((AuthPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
    }

    public static Long getAuthAccountId() {
        return ((AuthPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthAccountId();
    }
}
