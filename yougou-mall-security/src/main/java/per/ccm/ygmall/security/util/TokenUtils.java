package per.ccm.ygmall.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import per.ccm.ygmall.security.vo.TokenVO;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class TokenUtils {

    private static final String ISSUER = "yougou-mall-server";

    private static final String SECRET = "a33b61d310e14ddd99bd0e9fdec3f45d";

    private static final Long EXPIRED = 14 * 24 * 60 * 60 * 1000L;

    public static TokenVO getTokenVO(String token) {
        TokenVO tokenVO = new TokenVO();
        tokenVO.setAccessToken(token);
        tokenVO.setExpiredIn(new Date().getTime() + EXPIRED);
        return tokenVO;
    }

    public static String readToken(String bearer) {
        return bearer.substring(bearer.lastIndexOf(' ') + 1);
    }

    public static String generateToken(Long authAccountId, Long userId, String username, String authority) {
        Date currentDate = new Date();
        return JWT.create()
                .withClaim("auth_account_id", authAccountId)
                .withClaim("user_id", userId)
                .withClaim("username", username)
                .withClaim("authority", authority)
                .withIssuer(ISSUER)
                .withIssuedAt(currentDate)
                .withExpiresAt(new Date(currentDate.getTime() + EXPIRED))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static DecodedJWT verity(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET.getBytes(StandardCharsets.UTF_8))).build().verify(token);
    }
}
