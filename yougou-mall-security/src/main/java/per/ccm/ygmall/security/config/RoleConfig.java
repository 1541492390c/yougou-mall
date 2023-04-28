package per.ccm.ygmall.security.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleConfig {

    public static final String ROLE_SUFFIX = "ROLE_";

    public static final String SUPER_ADMIN = ROLE_SUFFIX + "SUPER_ADMIN";

    public static final String COMMON_ADMIN = ROLE_SUFFIX + "COMMON_ADMIN";

    public static final String USER = ROLE_SUFFIX + "USER";
}
