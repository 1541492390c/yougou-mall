package per.ccm.ygmall.extra.feign;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.feign.extra.feign.ExtraFeign;
import per.ccm.ygmall.extra.manager.CaptchaManager;
import per.ccm.ygmall.common.basic.response.ResponseEntity;

@Hidden
@RestController
public class ExtraController implements ExtraFeign {

    @Autowired
    private CaptchaManager captchaManager;

    @Override
    public ResponseEntity<Boolean> validateCaptcha(String ipAddress, String code) {
        Boolean validateResult = captchaManager.validate(ipAddress, code);
        return ResponseEntity.success(validateResult);
    }
}
