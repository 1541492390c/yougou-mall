package per.ccm.ygmall.extra.manager;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.ICaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.cache.cache.CacheNames;

import java.util.Objects;

@Component
public class CaptchaManager {

    @Autowired
    private RandomGenerator randomGenerator;

    @Autowired
    private CacheManager cacheManager;

    /**
     * 生成验证码
     *
     * @param ipAddress ip地址
     * @return 验证码
     * */
    public ICaptcha createCaptcha(String ipAddress) {
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(110, 40);
        circleCaptcha.setGenerator(randomGenerator);
        circleCaptcha.createCode();

        // 将验证码保存到缓存
        String code = circleCaptcha.getCode();
        Objects.requireNonNull(cacheManager.getCache(CacheNames.BIZ_VALIDATE_CODE_NAME)).put(ipAddress, code);
        return circleCaptcha;
    }

    /**
     * 验证验证码
     *
     * @param ipAddress ip地址
     * @param code 验证码
     * @return 是否验证成功
     * */
    public Boolean validate(String ipAddress, String code) {
        Cache cache = cacheManager.getCache(CacheNames.BIZ_VALIDATE_CODE_NAME);
        // 获取验证码
        String validateCode = Objects.requireNonNull(cache).get(ipAddress, String.class);
        if (!ObjectUtils.isEmpty(validateCode) && ObjectUtils.nullSafeEquals(validateCode.toLowerCase(), code.toLowerCase())) {
            // 验证成功,则返回true并清除验证码缓存
            cache.evict(ipAddress);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
