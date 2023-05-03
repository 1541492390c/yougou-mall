package per.ccm.ygmall.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import per.ccm.ygmall.security.enums.GrantType;

@Configuration
public class ClientConfig {

    public static final String YOUGOU_MALL_CLIENT_ID = "yougou-mall-client";

    public static final String YOUGOU_MALL_CLIENT_SECRET = "a33b61d310e14ddd99bd0e9fdec3f45d";

    public static final String YOUGOU_MALL_CLIENT_SCOPE = "all";

    public static final String[] YOUGOU_MALL_GRANT_TYPES = {
            GrantType.PASSWORD.value(),
            GrantType.REFRESH_TOKEN.value()
    };

    @Bean
    public ClientDetailsService inMemoryClientDetailsService() throws Exception {
        return new InMemoryClientDetailsServiceBuilder()
                .withClient(YOUGOU_MALL_CLIENT_ID)
                .secret(YOUGOU_MALL_CLIENT_SECRET)
                .scopes(YOUGOU_MALL_CLIENT_SCOPE)
                .authorizedGrantTypes(YOUGOU_MALL_GRANT_TYPES)
                .accessTokenValiditySeconds(2592000)
                .and().build();
    }
}
