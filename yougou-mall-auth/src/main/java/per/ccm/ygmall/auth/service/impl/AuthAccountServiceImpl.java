package per.ccm.ygmall.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import per.ccm.ygmall.api.user.feign.UserFeign;
import per.ccm.ygmall.auth.dto.AuthAccountDTO;
import per.ccm.ygmall.auth.dto.UpdatePasswordDTO;
import per.ccm.ygmall.auth.entity.AuthAccount;
import per.ccm.ygmall.auth.mapper.AuthAccountMapper;
import per.ccm.ygmall.auth.service.AuthAccountService;
import per.ccm.ygmall.auth.vo.AuthAccountVO;
import per.ccm.ygmall.cache.cache.CacheNames;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCodeEnum;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.security.enums.UserTypeEnum;
import per.ccm.ygmall.security.principal.AuthPrincipal;
import per.ccm.ygmall.security.config.RoleConfig;
import per.ccm.ygmall.security.util.TokenUtils;

import java.util.*;

@Service
public class AuthAccountServiceImpl implements AuthAccountService {

    @Autowired
    private AuthAccountMapper authAccountMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserFeign userFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<AuthAccount> queryWrapper = new LambdaQueryWrapper<>();

        AuthAccount authAccount = authAccountMapper.selectOne(queryWrapper.eq(AuthAccount::getUsername, username));
        // 用户不存在
        if (!authAccountMapper.exists(queryWrapper.eq(AuthAccount::getUsername, username))) {
            throw new YougouException(ResponseCodeEnum.USER_ERROR_A0002);
        }
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(RoleConfig.ROLE_SUFFIX + authAccount.getRole()));
        return new AuthPrincipal(authAccount.getAuthAccountId(), authAccount.getUserId(), authAccount.getUsername(), authAccount.getPassword(), authorities);
    }

    @Override
    public void save(AuthAccountDTO authAccountDTO) {
        LambdaQueryWrapper<AuthAccount> queryWrapper = new LambdaQueryWrapper<>();

        // 手机号已被使用
        if (authAccountMapper.exists(queryWrapper.eq(AuthAccount::getMp, authAccountDTO.getMp()))) {
            throw new YougouException(ResponseCodeEnum.USER_ERROR_A0008);
        }
        AuthAccount authAccount = ConvertUtils.convertProperties(authAccountDTO, AuthAccount.class);
        authAccount.setPassword(passwordEncoder.encode(authAccount.getPassword()));
        authAccountMapper.insert(authAccount);
    }

    @Override
    public String getToken(String ipAddress, String username, String password, String code, UserTypeEnum userTypeEnum) {
        // 添加附加信息
        Map<String, String> params = new HashMap<>();
        params.put("ip_address", ipAddress);
        params.put("username", username);
        params.put("password", password);
        params.put("code", code);
        params.put("type", userTypeEnum.getName());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
        token.setDetails(params);
        // 验证
        Authentication authentication = authenticationManager.authenticate(token);
        AuthPrincipal authPrincipal = (AuthPrincipal) authentication.getPrincipal();
        // 将认证token存入redis中
        String accessToken = TokenUtils.createToken(authPrincipal.getAuthAccountId(), authPrincipal.getUserId(), authPrincipal.getUsername(), authPrincipal.getAuthorities().iterator().next().getAuthority());
        Objects.requireNonNull(cacheManager.getCache(CacheNames.ACCESS_TOKEN_NAME)).put(authPrincipal.getUserId(), accessToken);
        return accessToken;
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
        //判断原密码与传入的密码是否一致
        if (!passwordEncoder.matches(updatePasswordDTO.getPassword(), authAccount.getPassword())) {
            throw new YougouException(ResponseCodeEnum.USER_ERROR_A0006);
        }

        String newPassword = passwordEncoder.encode(updatePasswordDTO.getNewPassword());
        authAccount.setPassword(newPassword);
        authAccountMapper.updateById(authAccount);
    }

    @Override
    public void update(AuthAccountDTO authAccountDTO) {
        AuthAccount authAccount = ConvertUtils.convertProperties(authAccountDTO, AuthAccount.class);
        authAccountMapper.updateById(authAccount);
    }

    @Override
    @CacheEvict(cacheNames = CacheNames.ACCESS_TOKEN_NAME, key = "#userId")
    public void removeToken(Long userId, String accessToken) throws Exception {
        userFeign.removerUserinfoCache(userId).responseSuccess();
    }
}
