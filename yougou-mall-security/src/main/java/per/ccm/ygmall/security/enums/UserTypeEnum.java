package per.ccm.ygmall.security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserTypeEnum {

    /**
     * 管理员
     * */
    ADMIN(0, "admin"),

    /**
     * 普通用户
     * */
    USER(1, "user");

    private final Integer value;

    private final String name;
}