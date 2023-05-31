package per.ccm.ygmall.user.feign;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.api.user.bo.UserBO;
import per.ccm.ygmall.api.user.feign.UserFeign;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.user.dto.UserUpdateDTO;

@Hidden
@RestController
public class UserFeignController implements UserFeign {

    @Override
    public ResponseEntity<Void> update(UserBO userBO) throws Exception {
        UserUpdateDTO userUpdateDTO = ConvertUtils.convertProperties(userBO, UserUpdateDTO.class);
        return null;
    }
}
