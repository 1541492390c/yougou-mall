package per.ccm.ygmall.user.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.common.security.enums.UserTypeEnum;
import per.ccm.ygmall.user.dto.UserRegisterDTO;
import per.ccm.ygmall.user.service.UserService;

@RestController("adminUserController")
@RequestMapping("/admin/user")
@Tag(name = "用户接口(管理员)", description = "用户接口(管理员)")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    @PreAuthorize("hasRole(@roleConfig.SUPER_ADMIN)")
    @Operation(summary = "保存用户(管理员)信息", description = "保存用户(管理员)信息")
    public ResponseEntity<Void> save(@RequestBody UserRegisterDTO userRegisterDTO) throws Exception {
        userRegisterDTO.setUserType(UserTypeEnum.ADMIN.getValue());
        userService.save(userRegisterDTO);
        return ResponseEntity.success();
    }
}
