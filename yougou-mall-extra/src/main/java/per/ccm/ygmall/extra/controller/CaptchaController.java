package per.ccm.ygmall.extra.controller;

import cn.hutool.captcha.ICaptcha;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.extra.manager.CaptchaManager;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/extra/captcha")
@Tag(name = "验证码接口", description = "验证码接口")
public class CaptchaController {

    @Autowired
    private CaptchaManager captchaManager;

    @GetMapping("/image")
    @Operation(summary = "获取验证码图片", description = "验证码图片")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        ICaptcha captcha = captchaManager.createCaptcha(request.getRemoteAddr());
        ServletOutputStream outputStream = response.getOutputStream();
        try (outputStream) {
            captcha.write(outputStream);
            outputStream.flush();
        }
    }
}
