package per.ccm.ygmall.extra.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.basic.exception.YougouException;
import per.ccm.ygmall.common.basic.response.ResponseCodeEnum;

@Slf4j
@Getter
@AllArgsConstructor
public enum ResourceTypeEnum {

    /**
     * 头像资源
     * */
    AVATAR(1, "/avatar/"),

    /**
     * 用户反馈资源
     * */
    FEEDBACK(2, "/feedback/"),

    /**
     * 用户评论资源
     * */
    COMMENT(3, "/comment/"),

    /**
     * 商品资源
     * */
    PRODUCT(4, "/product/"),

    /**
     * 品牌资源
     * */
    BRAND(5, "/brand/");

    private final Integer value;

    private final String path;

    public static ResourceTypeEnum getValueOf(Integer value) {
        for (ResourceTypeEnum resourceTypeEnum : ResourceTypeEnum.values()) {
            if (ObjectUtils.nullSafeEquals(resourceTypeEnum.getValue(), value)) {
                return resourceTypeEnum;
            }
        }
        log.error("ResourceTypeEnum is error --> value is not exist");
        throw new YougouException(ResponseCodeEnum.SERVER_ERROR_00001);
    }
}
