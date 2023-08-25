package per.ccm.ygmall.api.extra.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import per.ccm.ygmall.common.basic.config.FeignUrlConfig;
import per.ccm.ygmall.common.basic.response.ResponseEntity;

@FeignClient("yougou-mall-extra")
@RequestMapping(FeignUrlConfig.FEIGN_INNER_URL + "/extra")
public interface ExtraFeign {
    /**
     * 验证码是否正确
     *
     * @param ipAddress ip地址
     * @param code      验证码
     */
    @GetMapping("/captcha/validate")
    ResponseEntity<Boolean> validateCaptcha(@RequestParam("ip_address") String ipAddress, @RequestParam("code") String code);
}
