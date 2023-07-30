package per.ccm.ygmall.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

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
    COMMENT(3, "/comment/");

    private final Integer value;

    private final String path;

    public static ResourceTypeEnum getValueOf(Integer value) {
        for (ResourceTypeEnum resourceTypeEnum : ResourceTypeEnum.values()) {
            if (ObjectUtils.nullSafeEquals(resourceTypeEnum.getValue(), value)) {
                return resourceTypeEnum;
            }
        }
        throw new RuntimeException("ResourceTypeEnum is error --> value is not exist");
    }
}
