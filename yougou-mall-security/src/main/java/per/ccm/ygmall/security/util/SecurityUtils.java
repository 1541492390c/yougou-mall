package per.ccm.ygmall.security.util;

import org.springframework.security.core.context.SecurityContextHolder;
import per.ccm.ygmall.security.principal.AuthPrincipal;

public class SecurityUtils {

    public static Long getAuthAccountId() {
        return ((AuthPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthAccountId();
    }

    public static Long getUserId() {
        return ((AuthPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
    }
}
