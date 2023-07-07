package per.ccm.ygmall.auth.service;

import per.ccm.ygmall.auth.dto.AuthAccountDTO;
import per.ccm.ygmall.auth.dto.UpdatePasswordDTO;
import per.ccm.ygmall.auth.vo.AuthAccountVO;

/**
 * 认证授权服务
 */
public interface AuthAccountService {
    /**
     * 保存认证账号信息
     *
     * @param authAccountDTO 认证账号传输数据
     */
    void save(AuthAccountDTO authAccountDTO) throws Exception;

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
     * @param accessToken 认证token
     * */
    void removeToken(Long userId, String accessToken) throws Exception;
}
