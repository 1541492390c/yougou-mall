package per.ccm.ygmall.user.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.common.basic.vo.PageVO;
import per.ccm.ygmall.common.security.enums.UserTypeEnum;
import per.ccm.ygmall.user.dto.UserRegisterDTO;
import per.ccm.ygmall.user.dto.UserUpdateDTO;
import per.ccm.ygmall.user.entity.User;
import per.ccm.ygmall.user.service.UserService;
import per.ccm.ygmall.user.vo.UserVO;

@RestController("adminUserController")
@RequestMapping("/admin/user")
@PreAuthorize("hasAnyRole(@roleConfig.COMMON_ADMIN, @roleConfig.SUPER_ADMIN)")
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

    @GetMapping("/pages")
    @Operation(summary = "获取用户信息分页列表", description = "获取用户信息分页列表")
    @Parameters({
            @Parameter(name = "page_num", description = "当前页"),
            @Parameter(name = "page_size", description = "页数"),
            @Parameter(name = "user_type", description = "用户类型")
    })
    public ResponseEntity<PageVO<UserVO>> getUserPages(
            @RequestParam(value = "page_num", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "user_type", defaultValue = "2") Integer userType) throws Exception {
        Page<User> page = new Page<>(pageNum, pageSize);
        PageVO<UserVO> pageVO = userService.getUserPages(userType, page);
        return ResponseEntity.success(pageVO);
    }

    @PutMapping("/update_state")
    @PreAuthorize("hasRole(@roleConfig.SUPER_ADMIN)")
    @Operation(summary = "更新用户状态", description = "更新用户状态(0-禁用 1-正常)")
    @Parameters({
            @Parameter(name = "user_id", description = "用户ID"),
            @Parameter(name = "state", description = "状态(0-禁用 1-正常)")
    })
    public ResponseEntity<Void> updateState(@RequestParam("user_id") Long userId, @RequestParam("state") Integer state) throws Exception {
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        // 设置用户ID
        userUpdateDTO.setUserId(userId);
        // 设置状态
        userUpdateDTO.setState(state);
        // 更新用户信息
        userService.update(userUpdateDTO);
        return ResponseEntity.success();
    }
}
