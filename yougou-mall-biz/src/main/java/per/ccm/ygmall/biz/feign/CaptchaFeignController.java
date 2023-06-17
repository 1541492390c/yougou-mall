package per.ccm.ygmall.biz.feign;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.api.biz.feign.CaptchaFeign;
import per.ccm.ygmall.biz.manager.CaptchaManager;
import per.ccm.ygmall.common.response.ResponseEntity;

@Hidden
@RestController
public class CaptchaFeignController implements CaptchaFeign {

    @Autowired
    private CaptchaManager captchaManager;

    @Override
    public ResponseEntity<Boolean> validate(String ipAddress, String code) {
        Boolean validateResult = captchaManager.validate(ipAddress, code);
        return ResponseEntity.success(validateResult);
    }

    @Override
    public ResponseEntity<Void> removeCaptchaCache(String ipAddress) {
        captchaManager.removeCaptchaCache(ipAddress);
        return ResponseEntity.success();
    }
}
