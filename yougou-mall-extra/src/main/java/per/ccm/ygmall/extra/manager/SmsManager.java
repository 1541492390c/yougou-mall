package per.ccm.ygmall.extra.manager;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.aliyun.teautil.models.RuntimeOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.cache.cache.CacheNames;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCodeEnum;
import per.ccm.ygmall.common.util.JSONUtils;
import per.ccm.ygmall.common.util.RandomUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class SmsManager {

    @Autowired
    private Client client;

    @Autowired
    private CacheManager cacheManager;

    @Value("${spring.profiles.active}")
    private String active;

    /**
     * 发送手机验证码
     *
     * @param mobile 手机号
     * */
    public void sendSms(String mobile) throws Exception {
        String code = RandomUtils.randomMobileValidCode();
        // 模板参数
        Map<String, String> params = new HashMap<>();
        params.put("code", code);
        // 构建请求
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName("阿里云短信测试")
                .setTemplateCode("SMS_154950909")
                .setPhoneNumbers(mobile)
                .setTemplateParam(JSONUtils.writeValueAsString(params));
        RuntimeOptions runtimeOptions = new RuntimeOptions();
        // 发送验证码
        SendSmsResponseBody responseBody = client.sendSmsWithOptions(sendSmsRequest, runtimeOptions).getBody();
        // 将验证码保存到缓存
        if (ObjectUtils.nullSafeEquals(responseBody.getCode(), "OK")) {
            Objects.requireNonNull(cacheManager.getCache(CacheNames.BIZ_VALIDATE_CODE_NAME)).put(mobile, code);
        } else {
            log.info("{}", responseBody.getMessage());
            throw new YougouException(ResponseCodeEnum.SERVER_ERROR_00003);
        }
    }

    /**
     * 验证码验证
     *
     * @param code 验证码
     * @param mobile 手机号
     * */
    public void validate(String mobile, String code) {
        // 开发环境,验证码为111111
        if (ObjectUtils.nullSafeEquals(active, "dev") && ObjectUtils.nullSafeEquals(code, "111111")) {
            return;
        }
        Cache cache = Objects.requireNonNull(cacheManager.getCache(CacheNames.BIZ_VALIDATE_CODE_NAME));
        if (!ObjectUtils.nullSafeEquals(cache.get(mobile), code)) {
            cache.evict(mobile);
        }
        throw new YougouException(ResponseCodeEnum.AUTH_ERROR_A0010);
    }
}
