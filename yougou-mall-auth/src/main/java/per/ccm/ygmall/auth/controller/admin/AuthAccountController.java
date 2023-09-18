package per.ccm.ygmall.auth.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.auth.manager.LoginManager;
import per.ccm.ygmall.auth.service.AuthAccountService;
import per.ccm.ygmall.auth.vo.AuthAccountVO;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.common.security.enums.UserTypeEnum;
import per.ccm.ygmall.common.security.util.TokenUtils;
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
        String accessToken = loginManager.usernamePasswordLogin(request.getRemoteAddr(), username, password, code, ADMIN_TYPE);
        return ResponseEntity.success(TokenUtils.getTokenVO(accessToken));
    }

    @GetMapping("/get_by_user_id")
    @Operation(summary = "根据用户ID获取认证授权账号", description = "根据用户ID获取认证授权账号")
    @Parameter(name = "user_id", description = "用户ID", required = true)
    public ResponseEntity<AuthAccountVO> getAuthAccountByUserId(@RequestParam("user_id") Long userId) throws Exception {
        AuthAccountVO authAccountVO = authAccountService.getAuthAccountByUserId(userId);
        return ResponseEntity.success(authAccountVO);
    }
}
