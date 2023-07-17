package per.ccm.ygmall.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import per.ccm.ygmall.security.filter.JWTAuthenticationFilter;
import per.ccm.ygmall.security.handler.AuthenticationEntryPoint;
import per.ccm.ygmall.security.handler.PermissionDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import({JWTAuthenticationFilter.class, AuthenticationEntryPoint.class, PermissionDeniedHandler.class})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Lazy
    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private PermissionDeniedHandler permissionDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtAuthenticationFilter)
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(permissionDeniedHandler)
                .and()
                .authorizeRequests()
                // 放行接口
                .antMatchers(
                        // 文档
                        "/v3/**",
                        // 内部接口
                        "/yougou-mall-feign/**",
                        // 登录相关接口
                        "/auth/login", "/admin/auth/login",
                        // 用户相关接口
                        "/user/register", "/user/comment/rate_statistics",
                        // 商品相关接口
                        "/product/*", "/product/category/*", "/product/attr/*", "/product/sku/*",
                        // 平台相关接口
                        "/platform/**",
                        // 验证码接口
                        "/biz/captcha/*",
                        // 支付接口
                        "/payment/coupon/pages").permitAll()
                .anyRequest().authenticated()
                .and()
                .cors();
    }
}
