package per.ccm.ygmall.api.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import per.ccm.ygmall.api.user.bo.UserBO;
import per.ccm.ygmall.common.config.FeignUrlConfig;
import per.ccm.ygmall.common.response.ResponseEntity;

@FeignClient("yougou-mall-user")
@RequestMapping(FeignUrlConfig.FEIGN_INNER_URL + "/user")
public interface UserFeign {
    /**
     * 更新用户信息
     *
     * @param userBO 用户信息内部传输数据
     * */
    @PutMapping("/update")
    ResponseEntity<Void> update(@RequestBody UserBO userBO) throws Exception;
}
