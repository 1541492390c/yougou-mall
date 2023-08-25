package per.ccm.ygmall.feign.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import per.ccm.ygmall.feign.auth.bo.AuthAccountBO;
import per.ccm.ygmall.common.basic.config.FeignUrlConfig;
import per.ccm.ygmall.common.basic.response.ResponseEntity;

@FeignClient(value = "yougou-mall-auth", contextId = "auth")
@RequestMapping(FeignUrlConfig.FEIGN_INNER_URL + "/auth")
public interface AuthAccountFeign {
    /**
     * 保存认证账号信息
     *
     * @param authAccountBO 认证账号内部传输数据
     * @return 响应体
     * */
    @PostMapping( "/save")
    ResponseEntity<Void> save(@RequestBody AuthAccountBO authAccountBO) throws Exception;

    /**
     * 更新认证账号信息
     *
     * @param authAccountBO 认证账号内部传输数据
     * @return 响应体
     * */
    @PutMapping("/update")
    ResponseEntity<Void> update(@RequestBody AuthAccountBO authAccountBO) throws Exception;
}
