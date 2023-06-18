package per.ccm.ygmall.security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GrantTypeEnum {

    /**
     * 密码
     * */
    PASSWORD("password");

    private final String value;
}
