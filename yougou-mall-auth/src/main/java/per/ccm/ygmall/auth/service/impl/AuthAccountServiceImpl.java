package per.ccm.ygmall.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.auth.enums.UpdatePassTypeEnum;
import per.ccm.ygmall.feign.user.feign.UserFeign;
import per.ccm.ygmall.auth.dto.AuthAccountDTO;
import per.ccm.ygmall.auth.dto.UpdatePasswordDTO;
import per.ccm.ygmall.auth.entity.AuthAccount;
import per.ccm.ygmall.auth.mapper.AuthAccountMapper;
import per.ccm.ygmall.auth.service.AuthAccountService;
import per.ccm.ygmall.auth.vo.AuthAccountVO;
import per.ccm.ygmall.common.cache.cache.CacheNames;
import per.ccm.ygmall.common.basic.exception.YougouException;
import per.ccm.ygmall.common.basic.response.ResponseCodeEnum;
import per.ccm.ygmall.common.basic.util.ConvertUtils;

@Service
public class AuthAccountServiceImpl extends ServiceImpl<AuthAccountMapper, AuthAccount> implements AuthAccountService {

    @Autowired
    private AuthAccountMapper authAccountMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserFeign userFeign;

    @Override
    public void save(AuthAccountDTO authAccountDTO) {
        LambdaQueryWrapper<AuthAccount> queryWrapper = new LambdaQueryWrapper<>();

        // 用户名已被使用
        if (authAccountMapper.exists(queryWrapper.eq(AuthAccount::getUsername, authAccountDTO.getUsername()))) {
            throw new YougouException(ResponseCodeEnum.AUTH_ERROR_A0007);
        }
        // 手机号已被使用
        if (authAccountMapper.exists(queryWrapper.eq(AuthAccount::getMobile, authAccountDTO.getMobile()))) {
            throw new YougouException(ResponseCodeEnum.AUTH_ERROR_A0008);
        }
        AuthAccount authAccount = ConvertUtils.convertProperties(authAccountDTO, AuthAccount.class);
        authAccount.setPassword(passwordEncoder.encode(authAccount.getPassword()));
        authAccountMapper.insert(authAccount);
    }

    @Override
    public AuthAccountVO getAuthAccountByUserId(Long userId) {
        LambdaQueryWrapper<AuthAccount> queryWrapper = new LambdaQueryWrapper<>();
        AuthAccount authAccount = authAccountMapper.selectOne(queryWrapper.eq(AuthAccount::getUserId, userId));
        return ConvertUtils.convertProperties(authAccount, AuthAccountVO.class);
    }

    @Override
    public void updatePassword(Long userId, UpdatePasswordDTO updatePasswordDTO) {
        LambdaQueryWrapper<AuthAccount> queryWrapper = new LambdaQueryWrapper<>();

        AuthAccount authAccount = authAccountMapper.selectOne(queryWrapper.eq(AuthAccount::getUserId, userId));

        // 忘记密码
        if (ObjectUtils.nullSafeEquals(updatePasswordDTO.getUpdatePassType(), UpdatePassTypeEnum.FORGET.getValue())) {
            // 判断是否为绑定的手机号
            if (!ObjectUtils.nullSafeEquals(authAccount.getMobile(), updatePasswordDTO.getMobile())) {
                throw new YougouException(ResponseCodeEnum.AUTH_ERROR_A0012);
            }

            String newPassword = passwordEncoder.encode(updatePasswordDTO.getNewPassword());
            authAccount.setPassword(newPassword);
            authAccountMapper.updateById(authAccount);
        }
        // 修改密码
        if (ObjectUtils.nullSafeEquals(updatePasswordDTO.getUpdatePassType(), UpdatePassTypeEnum.UPDATE.getValue())) {
            // 原密码是否一致
            if (!passwordEncoder.matches(updatePasswordDTO.getPassword(), authAccount.getPassword())) {
                throw new YougouException(ResponseCodeEnum.AUTH_ERROR_A0013);
            }

            String newPassword = passwordEncoder.encode(updatePasswordDTO.getNewPassword());
            authAccount.setPassword(newPassword);
            authAccountMapper.updateById(authAccount);
        }
    }

    @Override
    public void update(AuthAccountDTO authAccountDTO) {
        AuthAccount authAccount = ConvertUtils.convertProperties(authAccountDTO, AuthAccount.class);
        authAccountMapper.updateById(authAccount);
    }

    @Override
    @CacheEvict(cacheNames = CacheNames.ACCESS_TOKEN_NAME, key = "#userId + ':' + #ipAddress")
    public void removeToken(Long userId, String ipAddress, String accessToken) throws Exception {
        userFeign.removerUserinfoCache(userId).responseSuccess();
    }

    @Override
    public AuthAccountVO getAuthAccountInfo(Long authAccountId) {
        AuthAccount authAccount = authAccountMapper.selectById(authAccountId);
        return ConvertUtils.convertProperties(authAccount, AuthAccountVO.class);
    }
}
