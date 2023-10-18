package per.ccm.ygmall.auth.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.auth.dto.AuthAccountDTO;
import per.ccm.ygmall.auth.manager.LoginManager;
import per.ccm.ygmall.auth.service.AuthAccountService;
import per.ccm.ygmall.auth.vo.AuthAccountVO;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.common.security.enums.UserTypeEnum;
import per.ccm.ygmall.common.security.vo.TokenVO;

import javax.servlet.http.HttpServletRequest;

@RestController("adminAuthAccountController")
@RequestMapping("/admin/auth")
@Tag(name = "认证授权接口(管理员)", description = "认证授权接口(管理员)")
public class AuthAccountController {

    @Autowired
    private AuthAccountService authAccountService;

    @Autowired
    private LoginManager loginManager;

    private static final UserTypeEnum ADMIN_TYPE = UserTypeEnum.ADMIN;

    @PostMapping("/login")
    @Operation(summary = "登录", description = "传入账号、密码进行登录")
    @Parameters({
            @Parameter(name = "username", description = "用户名", required = true),
            @Parameter(name = "password", description = "密码", required = true),
            @Parameter(name = "code", description = "验证码", required = true)
    })
    public ResponseEntity<TokenVO> login(
            HttpServletRequest request,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("code") String code) {
        TokenVO tokenVO = loginManager.usernamePasswordLogin(request.getRemoteAddr(), username, password, code, ADMIN_TYPE);
        return ResponseEntity.success(tokenVO);
    }

    @GetMapping("/get_one")
    @Operation(summary = "根据用户ID获取认证授权账号", description = "根据用户ID获取认证授权账号")
    @Parameter(name = "user_id", description = "用户ID", required = true)
    public ResponseEntity<AuthAccountVO> getAuthAccountByUserId(@RequestParam("user_id") Long userId) throws Exception {
        AuthAccountVO authAccountVO = authAccountService.getAuthAccountByUserId(userId);
        return ResponseEntity.success(authAccountVO);
    }

    @PutMapping("/update_role")
    @PreAuthorize("hasRole(@roleConfig.SUPER_ADMIN)")
    @Operation(summary = "更新用户(管理员)权限", description = "更新用户(管理员)权限")
    @Parameters({
            @Parameter(name = "auth_account_id", description = "管理员认证授权ID", required = true),
            @Parameter(name = "role", description = "管理员角色", required = true)
    })
    public ResponseEntity<Void> updateRole(@RequestParam("auth_account_id") Long authAccountId, @RequestParam("role") String role) throws Exception {
        AuthAccountDTO authAccountDTO = new AuthAccountDTO();
        // 设置认证授权ID
        authAccountDTO.setAuthAccountId(authAccountId);
        // 设置用户角色
        authAccountDTO.setRole(role);
        authAccountService.update(authAccountDTO);
        return ResponseEntity.success();
    }
}
