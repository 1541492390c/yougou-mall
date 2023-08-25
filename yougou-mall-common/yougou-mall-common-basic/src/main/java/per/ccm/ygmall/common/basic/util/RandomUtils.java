package per.ccm.ygmall.common.basic.util;

import java.util.Random;
import java.util.UUID;

public class RandomUtils {

    /**
     * 随机生成UUID
     *
     * @return 生成的UUID
     * */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 随机生成手机验证码
     *
     * @return 生成的手机验证码
     * */
    public static String randomMobileValidCode() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }
}
