package per.ccm.ygmall.security.util;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import per.ccm.ygmall.security.vo.TokenVO;

public class TokenUtils {

    public static TokenVO getTokenVO(OAuth2AccessToken token) {
        TokenVO tokenVO = new TokenVO();
        tokenVO.setAccessToken(token.getValue());
        tokenVO.setExpiredIn(token.getExpiresIn());
        return tokenVO;
    }
}
