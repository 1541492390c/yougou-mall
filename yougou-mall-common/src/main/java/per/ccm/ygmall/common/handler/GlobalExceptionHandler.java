package per.ccm.ygmall.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseEntity;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(YougouException.class)
    public ResponseEntity<Void> handle(YougouException e) {
        return ResponseEntity.fail(e.getResponseCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handle(Exception e) {
        log.error("{}", e.getMessage());
        return ResponseEntity.fail();
    }
}
