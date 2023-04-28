package per.ccm.ygmall.user.controller.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.security.util.SecurityUtils;
import per.ccm.ygmall.user.dto.UserDTO;
import per.ccm.ygmall.user.service.UserService;
import per.ccm.ygmall.user.vo.UserVO;

@RestController
@RequestMapping("/user")
@Tag(name = "用户接口", description = "用户接口")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @PreAuthorize("hasRole(@roleConfig.USER)")
    @Operation(summary = "用户注册", description = "用户注册")
    public ResponseEntity<Void> register(@RequestBody UserDTO userDTO) throws Exception {
        userService.save(userDTO);
        return ResponseEntity.success();
    }

    @GetMapping("/info")
    @Operation(summary = "获取用户信息", description = "获取用户信息")
    public ResponseEntity<UserVO> getUserinfo() throws Exception {
        Long userId = SecurityUtils.getUserId();
        UserVO userinfo = userService.getUserinfo(userId);
        return ResponseEntity.success(userinfo);
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户信息", description = "更新用户信息")
    public ResponseEntity<Void> update(@RequestBody UserDTO userDTO) throws Exception {
        Long userId = SecurityUtils.getUserId();

        userDTO.setUserId(userId);
        userService.update(userDTO);
        return ResponseEntity.success();
    }
}
