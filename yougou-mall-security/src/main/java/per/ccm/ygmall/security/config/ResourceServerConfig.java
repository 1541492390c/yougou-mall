package per.ccm.ygmall.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import per.ccm.ygmall.security.handler.AuthenticationEntryPoint;
import per.ccm.ygmall.security.handler.PermissionDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(new AuthenticationEntryPoint()).accessDeniedHandler(new PermissionDeniedHandler());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/v3/**").permitAll()
                .antMatchers("/yougou-mall-feign/**").permitAll()
                .antMatchers("/auth/login", "/admin/auth/login").permitAll()
                .antMatchers("/product/*", "/product/category/*", "/product/attr/*", "/product/sku/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .cors();
    }
}
