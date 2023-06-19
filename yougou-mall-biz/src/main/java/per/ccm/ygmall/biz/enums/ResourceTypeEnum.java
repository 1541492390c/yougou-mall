package per.ccm.ygmall.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.exception.ServerException;

@Getter
@AllArgsConstructor
public enum ResourceTypeEnum {

    /**
     * 头像资源
     * */
    AVATAR(1, "/avatar/");

    private final Integer value;

    private final String path;

    public static ResourceTypeEnum getValueOf(Integer value) {
        for (ResourceTypeEnum resourceTypeEnum : ResourceTypeEnum.values()) {
            if (ObjectUtils.nullSafeEquals(resourceTypeEnum.getValue(), value)) {
                return resourceTypeEnum;
            }
        }
        throw new ServerException("ResourceTypeEnum is error --> value is not exist");
    }
}
