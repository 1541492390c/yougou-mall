package per.ccm.ygmall.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import per.ccm.ygmall.cache.config.RedisConfig;
import per.ccm.ygmall.common.util.RandomUtils;

@Configuration
@Import(RedisConfig.class)
public class TokenConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(RandomUtils.randomUUID());
        jwtAccessTokenConverter.setSigner(new MacSigner(ClientConfig.YOUGOU_MALL_CLIENT_ID));
        return jwtAccessTokenConverter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }
}
