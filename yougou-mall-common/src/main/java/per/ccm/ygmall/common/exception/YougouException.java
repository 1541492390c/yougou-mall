package per.ccm.ygmall.common.exception;

import per.ccm.ygmall.common.response.ResponseCodeEnum;

public class YougouException extends RuntimeException {

    private ResponseCodeEnum responseCodeEnum;

    public YougouException(String message, ResponseCodeEnum responseCodeEnum) {
        super(message);
        this.responseCodeEnum = responseCodeEnum;
    }

    public YougouException(ResponseCodeEnum responseCodeEnum) {
        super(responseCodeEnum.getMessage());
        this.responseCodeEnum = responseCodeEnum;
    }

    public ResponseCodeEnum getResponseCode() {
        return responseCodeEnum;
    }

    public void responseCode(ResponseCodeEnum responseCodeEnum) {
        this.responseCodeEnum = responseCodeEnum;
    }
}
