package per.ccm.ygmall.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManagerBean;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private TokenStore tokenStore;

    @Bean
    public ClientDetailsService inMemoryClientDetailsServiceBuilder() throws Exception {
        return new InMemoryClientDetailsServiceBuilder()
                .withClient(ClientConfig.TIAOWA_TRAVEL_CLIENT_ID)
                .secret(ClientConfig.TIAOWA_TRAVEL_CLIENT_SECRET)
                .scopes(ClientConfig.TIAOWA_TRAVEL_CLIENT_SCOPE)
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(2592000)
                .and().build();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(inMemoryClientDetailsServiceBuilder());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManagerBean)
                .accessTokenConverter(jwtAccessTokenConverter)
                .tokenStore(tokenStore)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST, HttpMethod.GET);
    }
}
