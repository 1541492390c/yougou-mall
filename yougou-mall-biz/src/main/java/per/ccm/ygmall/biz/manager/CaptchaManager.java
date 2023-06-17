package per.ccm.ygmall.biz.manager;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ICaptcha;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.cache.cache.CacheNames;

@Component
public class CaptchaManager {

    @Autowired
    private RandomGenerator randomGenerator;

    @Autowired
    private CacheManager cacheManager;

    public ICaptcha createCaptcha(String ipAddress) {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(110, 40);
        lineCaptcha.setGenerator(randomGenerator);
        lineCaptcha.createCode();

        String code = lineCaptcha.getCode();
        Cache cache = cacheManager.getCache(CacheNames.BIZ_VALIDATE_CODE_NAME);

        if (!ObjectUtils.isEmpty(cache)) {
            cache.put(ipAddress, code);
        }
        return lineCaptcha;
    }

    public Boolean validate(String ipAddress, String code) {
        Cache cache = cacheManager.getCache(CacheNames.BIZ_VALIDATE_CODE_NAME);
        if (ObjectUtils.isEmpty(cache)) {
            return Boolean.FALSE;
        }
        if (ObjectUtils.isEmpty(cache.get(ipAddress, String.class))) {
            return Boolean.FALSE;
        }
        String validateCode = cache.get(ipAddress, String.class);
        if (!ObjectUtils.isEmpty(validateCode)) {
            return ObjectUtils.nullSafeEquals(validateCode.toLowerCase(), code);
        }
        return Boolean.FALSE;
    }
}
