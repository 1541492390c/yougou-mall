package per.ccm.ygmall.user.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.database.vo.PageVO;
import per.ccm.ygmall.security.util.SecurityContextUtils;
import per.ccm.ygmall.user.dto.UserRegisterDTO;
import per.ccm.ygmall.user.entity.User;
import per.ccm.ygmall.user.service.UserService;
import per.ccm.ygmall.user.vo.UserVO;

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
        userService.save(userRegisterDTO);
        return ResponseEntity.success();
    }

    @GetMapping("/pages")
    @PreAuthorize("hasAnyRole(@roleConfig.SUPER_ADMIN, @roleConfig.COMMON_ADMIN)")
    @Operation(summary = "分页获取用户信息列表", description = "分页获取用户信息列表")
    @Parameters({
            @Parameter(name = "page_num", description = "当前页"),
            @Parameter(name = "page_size", description = "页数"),
            @Parameter(name = "user_type", description = "用户类型 0-管理员 1-普通用户")})
    public ResponseEntity<PageVO<UserVO>> getUserPages(
            @RequestParam(value = "page_num", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "user_type", required = false) Integer userType) throws Exception {
        Long userId = SecurityContextUtils.getUserId();

        Page<User> page = new Page<>(pageNum, pageSize);
        PageVO<UserVO> pageVO = userService.getUserPages(userId, userType, page);
        return ResponseEntity.success(pageVO);
    }
}
