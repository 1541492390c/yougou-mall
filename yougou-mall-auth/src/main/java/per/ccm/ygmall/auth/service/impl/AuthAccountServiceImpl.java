package per.ccm.ygmall.auth.service.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAUpdateClause;
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
import per.ccm.ygmall.auth.entity.QAuthAccount;
import per.ccm.ygmall.auth.repository.AuthAccountRepository;
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
    private AuthAccountRepository authAccountRepository;

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QAuthAccount qAuthAccount = QAuthAccount.authAccount;
        AuthAccount authAccount = super.jpaQueryFactory.select(qAuthAccount).from(qAuthAccount)
                .where(qAuthAccount.username.eq(username)).fetchFirst();

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
        authAccountRepository.save(authAccount);
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
        QAuthAccount qAuthAccount = QAuthAccount.authAccount;
        QBean<AuthAccountVO> qBean = this.getQBean(qAuthAccount);

        return super.jpaQueryFactory.select(qBean).from(qAuthAccount).where(qAuthAccount.userId.eq(userId)).fetchFirst();
    }

    @Override
    public void updatePassword(Long authAccountId, UpdatePasswordDTO updatePasswordDTO) {
        QAuthAccount qAuthAccount = QAuthAccount.authAccount;

        AuthAccount authAccount = super.jpaQueryFactory.select(qAuthAccount).from(qAuthAccount)
                .where(qAuthAccount.authAccountId.eq(authAccountId)).fetchFirst();
        //判断原密码与传入的密码是否一致
        if (!passwordEncoder.matches(updatePasswordDTO.getPassword(), authAccount.getPassword())) {
            throw new YougouException(ResponseCode.USER_ERROR_A00006);
        }
        String newPassword = passwordEncoder.encode(updatePasswordDTO.getNewPassword());
        super.jpaQueryFactory.update(qAuthAccount).set(qAuthAccount.password, newPassword)
                .where(qAuthAccount.authAccountId.eq(authAccountId)).execute();
    }

    @Override
    public void update(AuthAccountDTO authAccountDTO) {
        QAuthAccount qAuthAccount = QAuthAccount.authAccount;

        JPAUpdateClause jpaUpdateClause = super.jpaQueryFactory.update(qAuthAccount);
        // 更新电子邮箱
        if (ObjectUtils.isEmpty(authAccountDTO.getEmail())) {
            jpaUpdateClause.set(qAuthAccount.email, authAccountDTO.getEmail());
        }
        // 更新手机号
        if (ObjectUtils.isEmpty(authAccountDTO.getMp())) {
            jpaUpdateClause.set(qAuthAccount.mp, authAccountDTO.getMp());
        }
        // 更新角色
        if (ObjectUtils.isEmpty(authAccountDTO.getRole())) {
            jpaUpdateClause.set(qAuthAccount.role, authAccountDTO.getRole());
        }
        jpaUpdateClause.where(qAuthAccount.authAccountId.eq(authAccountDTO.getAuthAccountId())).execute();
    }

    private QBean<AuthAccountVO> getQBean(QAuthAccount qAuthAccount) {
        return Projections.bean(AuthAccountVO.class, qAuthAccount.authAccountId, qAuthAccount.userId, qAuthAccount.state,
                qAuthAccount.account, qAuthAccount.username, qAuthAccount.email, qAuthAccount.mp);
    }
}
