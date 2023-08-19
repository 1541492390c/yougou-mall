package per.ccm.ygmall.biz.feign;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.api.biz.feign.BizFeign;
import per.ccm.ygmall.biz.manager.CaptchaManager;
import per.ccm.ygmall.common.response.ResponseEntity;

@Hidden
@RestController
public class BizController implements BizFeign {

    @Autowired
    private CaptchaManager captchaManager;

    @Override
    public ResponseEntity<Boolean> validateCaptcha(String ipAddress, String code) {
        Boolean validateResult = captchaManager.validate(ipAddress, code);
        return ResponseEntity.success(validateResult);
    }
}
