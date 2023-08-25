package per.ccm.ygmall.common.security.filter;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureGenerationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.cache.cache.CacheNames;
import per.ccm.ygmall.common.basic.response.ResponseCodeEnum;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.common.basic.util.JSONUtils;
import per.ccm.ygmall.common.security.principal.AuthPrincipal;
import per.ccm.ygmall.common.security.util.TokenUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    private CacheManager cacheManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        String bearer = request.getHeader("Authorization");

        // 认证token为空,放行交给其他过滤器
        if (ObjectUtils.isEmpty(bearer)) {
            chain.doFilter(request, response);
            return;
        }
        try {
            String accessToken = TokenUtils.readToken(bearer);
            DecodedJWT decodedJWT = TokenUtils.verity(accessToken);
            // 从token中获取认证授权ID
            Long authAccountId = decodedJWT.getClaim("auth_account_id").asLong();
            // 从token中获取用户ID
            Long userId = decodedJWT.getClaim("user_id").asLong();
            // 从token中获取用户名
            String username = decodedJWT.getClaim("username").asString();
            // 从token中获取ip地址
            String ipAddress = decodedJWT.getClaim("ip_address").asString();
            // 从token中获取用户角色
            String role = decodedJWT.getClaim("role").asString();

            Cache cache = cacheManager.getCache(CacheNames.ACCESS_TOKEN_NAME);

            // 获取缓存中的token
            String cacheAccessToken = Objects.requireNonNull(cache).get(TokenUtils.createTokenKey(userId, ipAddress), String.class);
            // 缓存中不存在token,token已过期
            if (ObjectUtils.isEmpty(cacheAccessToken)) {
                throw new TokenExpiredException("token已过期", Instant.now());
            }
            // 与缓存中的token不一致,无效token
            if (!ObjectUtils.nullSafeEquals(cacheAccessToken, accessToken)) {
                throw new SignatureVerificationException(Algorithm.HMAC256(accessToken.getBytes(StandardCharsets.UTF_8)));
            }
            // 添加授权范围
            List<SimpleGrantedAuthority> authorities = new ArrayList<>(Collections.singleton(new SimpleGrantedAuthority(role)));

            AuthPrincipal authPrincipal = new AuthPrincipal(authAccountId, userId, username, ipAddress, authorities);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authPrincipal, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(token);
            // 放行
            chain.doFilter(request, response);
        } catch (SignatureVerificationException | SignatureGenerationException | JWTDecodeException e) { // token验证失败
            String json = JSONUtils.writeValueAsString(ResponseEntity.fail(ResponseCodeEnum.AUTH_ERROR_A0003));
            response.getWriter().print(json);
        } catch (TokenExpiredException e) {  // token过期
            String json = JSONUtils.writeValueAsString(ResponseEntity.fail(ResponseCodeEnum.AUTH_ERROR_A0011));
            response.getWriter().print(json);
        } catch (Exception e) { // 其他错误
            log.error("{}", e.getMessage());
            String json = JSONUtils.writeValueAsString(ResponseCodeEnum.SERVER_ERROR_00001);
            response.getWriter().print(json);
        }
    }
}
