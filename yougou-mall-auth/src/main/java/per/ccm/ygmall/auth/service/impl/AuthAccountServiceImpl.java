package per.ccm.ygmall.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.auth.dto.AuthAccountDTO;
import per.ccm.ygmall.auth.dto.UpdatePasswordDTO;
import per.ccm.ygmall.auth.entity.AuthAccount;
import per.ccm.ygmall.auth.mapper.AuthAccountMapper;
import per.ccm.ygmall.auth.service.AuthAccountService;
import per.ccm.ygmall.auth.vo.AuthAccountVO;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCode;
import per.ccm.ygmall.common.service.BaseService;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.common.util.RandomUtils;
import per.ccm.ygmall.security.config.ClientConfig;
import per.ccm.ygmall.security.enums.GrantType;
import per.ccm.ygmall.security.enums.UserType;
import per.ccm.ygmall.security.principal.AuthPrincipal;
import per.ccm.ygmall.security.config.RoleConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthAccountServiceImpl extends BaseService implements AuthAccountService {

    @Autowired
    private AuthAccountMapper authAccountMapper;

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<AuthAccount> queryWrapper = new LambdaQueryWrapper<>();
        AuthAccount authAccount = authAccountMapper.selectOne(queryWrapper.eq(AuthAccount::getUsername, username));

        // 用户不存在
        if (ObjectUtils.isEmpty(authAccount)) {
            throw new YougouException(ResponseCode.USER_ERROR_A00002);
        }
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(RoleConfig.ROLE_SUFFIX + authAccount.getRole()));
        return new AuthPrincipal(authAccount.getAuthAccountId(), authAccount.getUserId(), authAccount.getAccount(), authAccount.getPassword(), authorities);
    }

    @Override
    public void save(AuthAccountDTO authAccountDTO) {
        AuthAccount authAccount = ConvertUtils.dtoConvertToEntity(authAccountDTO, AuthAccount.class);
        authAccount.setAccount(RandomUtils.createAccount());
        authAccount.setPassword(passwordEncoder.encode(authAccount.getPassword()));
        authAccount.setEnabled(true);
        authAccountMapper.insert(authAccount);
    }

    @Override
    public OAuth2AccessToken getToken(String username, String password, UserType userType) throws Exception {
        UserDetails userDetails = new User(ClientConfig.TIAOWA_TRAVEL_CLIENT_ID, ClientConfig.TIAOWA_TRAVEL_CLIENT_SECRET, new ArrayList<>());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, new ArrayList<>());

        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("type", userType.value());
        params.put("grant_type", GrantType.PASSWORD.value());
        return tokenEndpoint.postAccessToken(token, params).getBody();
    }

    @Override
    public AuthAccountVO getAuthAccountByUserId(Long userId) {
        LambdaQueryWrapper<AuthAccount> queryWrapper = new LambdaQueryWrapper<>();
        AuthAccount authAccount = authAccountMapper.selectOne(queryWrapper.eq(AuthAccount::getUserId, userId));
        return ConvertUtils.entityConvertToVO(authAccount, AuthAccountVO.class);
    }

    @Override
    public void updatePassword(Long authAccountId, UpdatePasswordDTO updatePasswordDTO) {
        LambdaQueryWrapper<AuthAccount> queryWrapper = new LambdaQueryWrapper<>();

        AuthAccount authAccount = authAccountMapper.selectOne(queryWrapper.eq(AuthAccount::getAuthAccountId, authAccountId));
        //判断原密码与传入的密码是否一致
        if (!passwordEncoder.matches(updatePasswordDTO.getPassword(), authAccount.getPassword())) {
            throw new YougouException(ResponseCode.USER_ERROR_A00006);
        }

        String newPassword = passwordEncoder.encode(updatePasswordDTO.getNewPassword());
        authAccount.setPassword(newPassword);
        authAccountMapper.updateById(authAccount);
    }

    @Override
    public void update(AuthAccountDTO authAccountDTO) {
        AuthAccount authAccount = ConvertUtils.dtoConvertToEntity(authAccountDTO, AuthAccount.class);
        authAccountMapper.updateById(authAccount);
    }
}