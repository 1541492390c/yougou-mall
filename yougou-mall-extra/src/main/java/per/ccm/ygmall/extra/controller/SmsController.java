package per.ccm.ygmall.extra.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.extra.manager.SmsManager;
import per.ccm.ygmall.common.response.ResponseEntity;

@RestController
@RequestMapping("/biz/sms")
public class SmsController {

    @Autowired
    private SmsManager smsManager;

    @PostMapping("/send")
    @Parameter(name = "mobile", description = "手机号")
    public ResponseEntity<Void> sendSms(String mobile) throws Exception {
        smsManager.sendSms(mobile);
        return ResponseEntity.success();
    }

    @PostMapping("/validate")
    @Parameters({
            @Parameter(name = "mobile", description = "手机号"),
            @Parameter(name = "code", description = "验证码")
    })
    public ResponseEntity<Void> validate(String mobile, String code) {
        smsManager.validate(mobile, code);
        return ResponseEntity.success();
    }
}
