package per.ccm.ygmall.common.basic.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.common.basic.exception.YougouException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(YougouException.class)
    public ResponseEntity<Void> handle(YougouException e) {
        return ResponseEntity.fail(e.getResponseCode());
    }
}
