package per.ccm.ygmall.common.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import per.ccm.ygmall.common.security.principal.AuthPrincipal;
import per.ccm.ygmall.common.security.vo.TokenVO;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class TokenUtils {

    private static final String ISSUER = "yougou-mall-server";

    private static final String SECRET = "a33b61d310e14ddd99bd0e9fdec3f45d";

    private static final Long EXPIRED = 14 * 24 * 60 * 60 * 1000L;

    public static TokenVO getTokenVO(String accessToken) {
        TokenVO tokenVO = new TokenVO();
        tokenVO.setAccessToken(accessToken);
        tokenVO.setExpiredIn(new Date().getTime() + EXPIRED);
        return tokenVO;
    }

    public static String readToken(String bearer) {
        return bearer.substring(bearer.lastIndexOf(' ') + 1);
    }

    public static String createTokenKey(Long userId, String ipAddress) {
        return userId + ":" + ipAddress;
    }

    public static String createToken(AuthPrincipal authPrincipal) {
        Date currentDate = new Date();
        return JWT.create()
                .withClaim("auth_account_id", authPrincipal.getAuthAccountId())
                .withClaim("user_id", authPrincipal.getUserId())
                .withClaim("username", authPrincipal.getUsername())
                .withClaim("ip_address", authPrincipal.getIpAddress())
                .withClaim("role", authPrincipal.getAuthorities().get(0).getAuthority())
                .withIssuer(ISSUER)
                .withIssuedAt(currentDate)
                .withExpiresAt(new Date(currentDate.getTime() + EXPIRED))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static DecodedJWT verity(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET.getBytes(StandardCharsets.UTF_8))).build().verify(token);
    }
}
