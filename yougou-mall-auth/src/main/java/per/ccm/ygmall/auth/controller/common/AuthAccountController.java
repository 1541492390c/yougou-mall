package per.ccm.ygmall.auth.controller.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.auth.dto.UpdatePasswordDTO;
import per.ccm.ygmall.auth.service.AuthAccountService;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.security.enums.UserType;
import per.ccm.ygmall.security.util.SecurityContextUtils;
import per.ccm.ygmall.security.util.TokenUtils;
import per.ccm.ygmall.security.vo.TokenVO;

@RestController
@RequestMapping("/auth")
@Tag(name = "认证授权(通用)", description = "认证授权(通用)")
public class AuthAccountController {

    @Autowired
    private AuthAccountService authAccountService;

    private static final UserType USER_TYPE = UserType.USER;

    @PostMapping("/login")
    @Operation(summary = "登录", description = "传入账号、密码进行登录")
    @Parameters({
            @Parameter(name = "username", description = "用户名(账号)", required = true),
            @Parameter(name = "password", description = "密码", required = true)})
    public ResponseEntity<TokenVO> login(@RequestParam("username") String username, @RequestParam("password") String password) throws Exception {
        OAuth2AccessToken token = authAccountService.getToken(username, password, USER_TYPE);
        return ResponseEntity.success(TokenUtils.getTokenVO(token));
    }

    @PutMapping("/update_password")
    @Operation(summary = "更新密码", description = "更新密码")
    public ResponseEntity<Void> updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO) throws Exception {
        Long userId = SecurityContextUtils.getUserId();
        authAccountService.updatePassword(userId, updatePasswordDTO);
        return ResponseEntity.success();
    }
}
