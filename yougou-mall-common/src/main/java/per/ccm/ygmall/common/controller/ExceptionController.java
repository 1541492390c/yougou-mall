package per.ccm.ygmall.common.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import per.ccm.ygmall.common.exception.ServerException;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCode;
import per.ccm.ygmall.common.response.ResponseEntity;

@RestControllerAdvice
public class ExceptionController extends BaseController {

    @ExceptionHandler(YougouException.class)
    public ResponseEntity<Void> handle(YougouException e) {
        return ResponseEntity.fail(e.getResponseCode());
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<Void> handle(ServerException e) {
        super.logger().error("{}", e.getMessage());
        return ResponseEntity.fail(ResponseCode.SERVER_ERROR_000001);
    }
}
