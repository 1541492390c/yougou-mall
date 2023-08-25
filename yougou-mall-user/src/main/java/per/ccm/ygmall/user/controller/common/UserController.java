package per.ccm.ygmall.user.controller.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.common.security.enums.UserTypeEnum;
import per.ccm.ygmall.common.security.util.SecurityContextUtils;
import per.ccm.ygmall.user.dto.UserRegisterDTO;
import per.ccm.ygmall.user.dto.UserUpdateDTO;
import per.ccm.ygmall.user.service.UserService;
import per.ccm.ygmall.user.vo.UserVO;

@RestController
@RequestMapping("/user")
@Tag(name = "用户接口", description = "用户接口")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @Operation(summary = "用户注册", description = "用户注册")
    public ResponseEntity<Void> register(@RequestBody UserRegisterDTO userRegisterDTO) throws Exception {
        userRegisterDTO.setUserType(UserTypeEnum.USER.getValue());
        userService.save(userRegisterDTO);
        return ResponseEntity.success();
    }

    @GetMapping("/info")
    @Operation(summary = "获取用户信息", description = "获取用户信息")
    public ResponseEntity<UserVO> getUserinfo() throws Exception {
        Long userId = SecurityContextUtils.getUserId();
        UserVO userinfo = userService.getUserinfo(userId);
        return ResponseEntity.success(userinfo);
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户信息", description = "更新用户信息")
    public ResponseEntity<Void> update(@RequestBody UserUpdateDTO userUpdateDTO) throws Exception {
        Long userId = SecurityContextUtils.getUserId();

        userUpdateDTO.setUserId(userId);
        userService.update(userUpdateDTO);
        return ResponseEntity.success();
    }
}
