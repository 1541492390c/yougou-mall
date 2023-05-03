package per.ccm.ygmall.common.util;

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
}
