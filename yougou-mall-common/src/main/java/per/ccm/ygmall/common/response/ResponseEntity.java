package per.ccm.ygmall.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.ObjectUtils;

@Getter
@Setter
@ToString
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
        responseEntity.setCode(ResponseCodeEnum.OK.getValue());
        responseEntity.setMessage(ResponseCodeEnum.OK.getMessage());
        return responseEntity;
    }

    public static <T> ResponseEntity<T> success(T data) {
        ResponseEntity<T> responseEntity = new ResponseEntity<>();
        responseEntity.setCode(ResponseCodeEnum.OK.getValue());
        responseEntity.setMessage(ResponseCodeEnum.OK.getMessage());
        responseEntity.setData(data);
        return responseEntity;
    }

    public static <T> ResponseEntity<T> fail() {
        ResponseEntity<T> responseEntity = new ResponseEntity<>();
        responseEntity.setCode(ResponseCodeEnum.SERVER_ERROR_000001.getValue());
        responseEntity.setMessage(ResponseCodeEnum.SERVER_ERROR_000001.getMessage());
        return responseEntity;
    }

    public static <T> ResponseEntity<T> fail(ResponseCodeEnum responseCodeEnum) {
        ResponseEntity<T> responseEntity = new ResponseEntity<>();
        responseEntity.setCode(responseCodeEnum.getValue());
        responseEntity.setMessage(responseCodeEnum.getMessage());
        return responseEntity;
    }

    public Boolean responseSuccess() {
        return ObjectUtils.nullSafeEquals(this.code, ResponseCodeEnum.OK.getValue());
    }
}