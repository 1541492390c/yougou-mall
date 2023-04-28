package per.ccm.ygmall.security.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import per.ccm.ygmall.common.vo.BaseVO;

public class TokenVO extends BaseVO {

    @Schema(description = "认证token")
    private String accessToken;

    @Schema(description = "刷新token")
    private String refreshToken;

    @Schema(description = "过期时间")
    private Integer expiredIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Integer getExpiredIn() {
        return expiredIn;
    }

    public void setExpiredIn(Integer expiredIn) {
        this.expiredIn = expiredIn;
    }

    @Override
    public String toString() {
        return "TokenVO{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", expiredIn=" + expiredIn +
                '}';
    }
}
