package per.ccm.ygmall.security.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleConfig {

    /**
     * 超级管理员
     * */
    public static final String SUPER_ADMIN = "ROLE_SUPER_ADMIN";

    /**
     * 普通管理员
     * */
    public static final String COMMON_ADMIN = "ROLE_COMMON_ADMIN";

    /**
     * 普通用户
     * */
    public static final String USER = "ROLE_USER";
}
