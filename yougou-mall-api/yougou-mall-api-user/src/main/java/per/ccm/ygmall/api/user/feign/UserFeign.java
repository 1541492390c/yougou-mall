package per.ccm.ygmall.api.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.api.user.bo.UserBO;
import per.ccm.ygmall.common.config.FeignUrlConfig;
import per.ccm.ygmall.common.response.ResponseEntity;

@FeignClient("yougou-mall-user")
@RequestMapping(FeignUrlConfig.FEIGN_INNER_URL + "/user")
public interface UserFeign {
    /**
     * 更新用户信息内部接口
     *
     * @param userBO 用户信息内部传输数据
     * */
    @PutMapping("/update")
    ResponseEntity<Void> update(@RequestBody UserBO userBO) throws Exception;

    /**
     * 移除用户信息缓存
     *
     * @param userId 用户ID
     * */
    @GetMapping("/remove_userinfo_cache")
    ResponseEntity<Void> removerUserinfoCache(@RequestParam("user_id") Long userId) throws Exception;
}
