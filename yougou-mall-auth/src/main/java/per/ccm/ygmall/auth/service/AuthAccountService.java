package per.ccm.ygmall.auth.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import per.ccm.ygmall.auth.dto.AuthAccountDTO;
import per.ccm.ygmall.auth.dto.UpdatePasswordDTO;
import per.ccm.ygmall.auth.vo.AuthAccountVO;
import per.ccm.ygmall.security.enums.UserTypeEnum;

/**
 * 认证授权服务
 */
public interface AuthAccountService extends UserDetailsService {
    /**
     * 保存认证账号信息
     *
     * @param authAccountDTO 认证账号传输数据
     */
    void save(AuthAccountDTO authAccountDTO) throws Exception;

    /**
     * 获取token(用户名密码模式)
     *
     * @param ipAddress 登录者IP地址
     * @param username  用户名
     * @param password  密码
     * @return token
     */
    OAuth2AccessToken getToken(String ipAddress, String username, String password, String code, UserTypeEnum userTypeEnum) throws Exception;

    /**
     * 根据用户ID获取认证授权账号信息
     *
     * @param userId 用户ID
     * @return 认证授权账号信息
     */
    AuthAccountVO getAuthAccountByUserId(Long userId) throws Exception;

    /**
     * 更新密码
     *
     * @param userId            用户ID
     * @param updatePasswordDTO 更新密码传输数据
     */
    void updatePassword(Long userId, UpdatePasswordDTO updatePasswordDTO) throws Exception;

    /**
     * 更新认证账号信息
     *
     * @param authAccountDTO 认证账号传输数据
     */
    void update(AuthAccountDTO authAccountDTO) throws Exception;

    /**
     * 移除token
     *
     * @param userId 用户ID
     * @param token 认证token
     * */
    void removeToken(Long userId, String token) throws Exception;
}
