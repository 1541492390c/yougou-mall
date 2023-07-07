package per.ccm.ygmall.auth.controller.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.auth.dto.UpdatePasswordDTO;
import per.ccm.ygmall.auth.manager.LoginManager;
import per.ccm.ygmall.auth.service.AuthAccountService;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.security.enums.UserTypeEnum;
import per.ccm.ygmall.security.util.SecurityContextUtils;
import per.ccm.ygmall.security.util.TokenUtils;
import per.ccm.ygmall.security.vo.TokenVO;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
@Tag(name = "认证授权接口", description = "认证授权接口")
public class AuthAccountController {

    @Autowired
    private AuthAccountService authAccountService;

    @Autowired
    private LoginManager loginManager;

    private static final UserTypeEnum USER_TYPE = UserTypeEnum.USER;

    @PostMapping("/login")
    @Operation(summary = "登录", description = "传入账号、密码进行登录")
    @Parameters({
            @Parameter(name = "username", description = "用户名", required = true),
            @Parameter(name = "password", description = "密码", required = true),
            @Parameter(name = "code", description = "验证码", required = true)})
    public ResponseEntity<TokenVO> login(
            HttpServletRequest request,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("code") String code) {
        String accessToken = loginManager.usernamePasswordLogin(request.getRemoteAddr(), username, password, code, USER_TYPE);
        return ResponseEntity.success(TokenUtils.getTokenVO(accessToken));
    }

    @PutMapping("/update_password")
    @Operation(summary = "更新密码", description = "更新密码")
    public ResponseEntity<Void> updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO) throws Exception {
        Long userId = SecurityContextUtils.getUserId();
        authAccountService.updatePassword(userId, updatePasswordDTO);
        return ResponseEntity.success();
    }

    @GetMapping("/logout")
    @Operation(summary = "退出登录", description = "退出登录")
    public ResponseEntity<Void> logout(HttpServletRequest request) throws Exception {
        Long userId = SecurityContextUtils.getUserId();

        String bearer = request.getHeader("Authorization");
        String accessToken = TokenUtils.readToken(bearer);
        authAccountService.removeToken(userId, accessToken);
        return ResponseEntity.success();
    }
}
