package per.ccm.ygmall.common.basic.exception;

import lombok.Getter;
import per.ccm.ygmall.common.basic.response.ResponseCodeEnum;

@Getter
public class YougouException extends RuntimeException {

    private final ResponseCodeEnum responseCode;

    public YougouException(String message, ResponseCodeEnum responseCodeEnum) {
        super(message);
        this.responseCode = responseCodeEnum;
    }

    public YougouException(ResponseCodeEnum responseCodeEnum) {
        super(responseCodeEnum.getMessage());
        this.responseCode = responseCodeEnum;
    }
}
