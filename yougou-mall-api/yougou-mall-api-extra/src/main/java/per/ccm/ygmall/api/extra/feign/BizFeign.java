package per.ccm.ygmall.api.extra.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import per.ccm.ygmall.common.config.FeignUrlConfig;
import per.ccm.ygmall.common.response.ResponseEntity;

@FeignClient("yougou-mall-biz")
@RequestMapping(FeignUrlConfig.FEIGN_INNER_URL + "/biz")
public interface BizFeign {
    /**
     * 验证码是否正确
     *
     * @param ipAddress ip地址
     * @param code      验证码
     */
    @GetMapping("/captcha/validate")
    ResponseEntity<Boolean> validateCaptcha(@RequestParam("ip_address") String ipAddress, @RequestParam("code") String code);
}
