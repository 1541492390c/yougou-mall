package per.ccm.ygmall.extra.config;

import cn.hutool.captcha.generator.RandomGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaptchaConfig {

    @Bean
    public RandomGenerator randomGenerator() {
        return new RandomGenerator("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", 4);
    }
}
