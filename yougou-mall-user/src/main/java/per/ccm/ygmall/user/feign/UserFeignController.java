package per.ccm.ygmall.user.feign;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.api.user.bo.UserBO;
import per.ccm.ygmall.api.user.feign.UserFeign;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.common.basic.util.ConvertUtils;
import per.ccm.ygmall.user.dto.UserUpdateDTO;
import per.ccm.ygmall.user.service.UserService;

@Hidden
@RestController
public class UserFeignController implements UserFeign {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<Void> update(UserBO userBO) throws Exception {
        UserUpdateDTO userUpdateDTO = ConvertUtils.convertProperties(userBO, UserUpdateDTO.class);
        userService.update(userUpdateDTO);
        return ResponseEntity.success();
    }

    @Override
    public ResponseEntity<Void> removerUserinfoCache(Long userId) throws Exception {
        userService.removeUserinfoCache(userId);
        return ResponseEntity.success();
    }
}
