package per.ccm.ygmall.payment.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AliPayConfig {

    @Value("${alipay.app-id}")
    private String appId;

    @Value("${alipay.alipay-public-key}")
    private String aliPayPublicKey;

    @Value("${alipay.private-key}")
    private String privateKey;

    @Value("${alipay.sign-type}")
    private String signType;

    @Value("${alipay.server-url}")
    private String serverUrl;

    @Value("${alipay.return-url}")
    private String returnUrl;

    @Value("${alipay.notify-url}")
    private String notifyUrl;

    @Bean
    public AlipayClient alipayClient() throws AlipayApiException {
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setAppId(appId);
        alipayConfig.setAlipayPublicKey(aliPayPublicKey);
        alipayConfig.setPrivateKey(privateKey);
        alipayConfig.setServerUrl(serverUrl);
        alipayConfig.setSignType(signType);
        alipayConfig.setFormat("JSON");
        alipayConfig.setCharset("UTF-8");
        return new DefaultAlipayClient(alipayConfig);
    }
}
