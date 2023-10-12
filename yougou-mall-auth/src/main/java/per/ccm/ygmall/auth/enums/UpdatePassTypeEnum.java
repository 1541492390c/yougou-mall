package per.ccm.ygmall.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UpdatePassTypeEnum {

    FORGET(1, "forget"),

    UPDATE(2, "update");

    private final Integer value;

    private final String message;
}
