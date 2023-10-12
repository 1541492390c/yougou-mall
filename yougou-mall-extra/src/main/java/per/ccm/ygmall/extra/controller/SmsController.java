package per.ccm.ygmall.extra.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.extra.manager.SmsManager;
import per.ccm.ygmall.common.basic.response.ResponseEntity;

@RestController
@RequestMapping("/extra/sms")
@Tag(name = "手机短信接口", description = "手机短信接口")
public class SmsController {

    @Autowired
    private SmsManager smsManager;

    @PostMapping("/send")
    @Operation(summary = "发送短信验证码", description = "发送短信验证码")
    @Parameter(name = "mobile", description = "手机号")
    public ResponseEntity<Void> sendSms(String mobile) throws Exception {
        smsManager.sendSms(mobile);
        return ResponseEntity.success();
    }

    @PostMapping("/validate")
    @Operation(summary = "验证短信验证码", description = "验证短信验证码")
    @Parameters({
            @Parameter(name = "mobile", description = "手机号"),
            @Parameter(name = "code", description = "验证码")
    })
    public ResponseEntity<Void> validate(String mobile, String code) {
        smsManager.validate(mobile, code);
        return ResponseEntity.success();
    }
}
