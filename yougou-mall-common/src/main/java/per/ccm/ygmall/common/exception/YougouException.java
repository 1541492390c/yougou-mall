package per.ccm.ygmall.common.exception;

import per.ccm.ygmall.common.response.ResponseCode;

public class YougouException extends RuntimeException {

    private ResponseCode responseCode;

    public YougouException(String message, ResponseCode responseCode) {
        super(message);
        this.responseCode = responseCode;
    }

    public YougouException(ResponseCode responseCode) {
        super(responseCode.message());
        this.responseCode = responseCode;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void responseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }
}
