package per.ccm.ygmall.common.util;

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
     * 随机生成11位账号
     *
     * @return 生成的账号
     * */
    public static String createAccount() {
        Random random = new Random();

        int max = 1000000000;
        int min = 100000000;
        int[] num = {2, 4, 6, 8};
        return "1" + num[random.nextInt(num.length)] + (random.nextInt(max) % (max - min) + min);
    }
}
