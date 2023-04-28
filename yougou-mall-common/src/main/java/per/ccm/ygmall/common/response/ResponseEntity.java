package per.ccm.ygmall.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseEntity<T> {

    @Schema(description = "响应信息")
    private String message;

    @Schema(description = "请求状态码")
    private String code;

    @Schema(description = "返回数据")
    private T data;

    public static ResponseEntity<Void> success() {
        ResponseEntity<Void> responseEntity = new ResponseEntity<>();
        responseEntity.setCode(ResponseCode.OK.value());
        responseEntity.setMessage(ResponseCode.OK.message());
        return responseEntity;
    }

    public static <T> ResponseEntity<T> success(T data) {
        ResponseEntity<T> responseEntity = new ResponseEntity<>();
        responseEntity.setCode(ResponseCode.OK.value());
        responseEntity.setMessage(ResponseCode.OK.message());
        responseEntity.setData(data);
        return responseEntity;
    }

    public static <T> ResponseEntity<T> fail() {
        ResponseEntity<T> responseEntity = new ResponseEntity<>();
        responseEntity.setCode(ResponseCode.SERVER_ERROR_000001.value());
        responseEntity.setMessage(ResponseCode.SERVER_ERROR_000001.message());
        return responseEntity;
    }

    public static <T> ResponseEntity<T> fail(ResponseCode responseCode) {
        ResponseEntity<T> responseEntity = new ResponseEntity<>();
        responseEntity.setCode(responseCode.value());
        responseEntity.setMessage(responseCode.message());
        return responseEntity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}