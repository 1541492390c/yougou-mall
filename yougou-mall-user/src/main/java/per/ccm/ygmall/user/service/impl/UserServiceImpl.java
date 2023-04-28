package per.ccm.ygmall.user.service.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.api.auth.bo.AuthAccountBO;
import per.ccm.ygmall.api.auth.feign.AuthAccountFeign;
import per.ccm.ygmall.common.cache.CacheNames;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCode;
import per.ccm.ygmall.common.service.BaseService;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.common.vo.PageVO;
import per.ccm.ygmall.user.dto.UserDTO;
import per.ccm.ygmall.user.entity.QUser;
import per.ccm.ygmall.user.entity.User;
import per.ccm.ygmall.user.repository.UserRepository;
import per.ccm.ygmall.user.service.UserService;
import per.ccm.ygmall.user.vo.UserVO;

import java.util.List;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthAccountFeign authAccountFeign;

    @Override
    public void save(UserDTO userDTO) throws Exception {
        QUser qUser = QUser.user;

        // 判断用户名是否存在
        if (userRepository.exists(qUser.username.eq(userDTO.getUsername()))) {
            throw new YougouException(ResponseCode.USER_ERROR_A00007);
        }
        User user = ConvertUtils.dtoConvertToEntity(userDTO, User.class);
        userRepository.save(user);

        AuthAccountBO authAccountBO = ConvertUtils.dtoConvertToBO(userDTO, AuthAccountBO.class);
        authAccountBO.setUserId(user.getUserId());
        authAccountBO.setUsername(user.getUsername());
        authAccountFeign.save(authAccountBO);
    }

    @Override
    @Cacheable(cacheNames = CacheNames.USERINFO_CACHE_NAME, key = "#userId", sync = true)
    public UserVO getUserinfo(Long userId) {
        User user = userRepository.getOne(userId);
        return ConvertUtils.entityConvertToVO(user, UserVO.class);
    }

    @Override
    public PageVO<UserVO> getUserPages(Long userId, Integer userType, Pageable pageable) {
        QUser qUser = QUser.user;
        QBean<UserVO> qBean = this.getQBean(qUser);

        Long total = userRepository.count();
        List<UserVO> userList = super.jpaQueryFactory.select(qBean).from(qUser).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
        return new PageVO<>(total, userList);
    }

    @Override
    @CacheEvict(cacheNames = CacheNames.USERINFO_CACHE_NAME)
    public void update(UserDTO userDTO) throws Exception {
        QUser qUser = QUser.user;

        JPAUpdateClause jpaUpdateClause = jpaQueryFactory.update(qUser);
        // 更新头像地址
        if (ObjectUtils.isEmpty(userDTO.getAvatar())) {
            jpaUpdateClause.set(qUser.avatar, userDTO.getAvatar());
        }
        // 更新性别
        if (ObjectUtils.isEmpty(userDTO.getGender())) {
            jpaUpdateClause.set(qUser.gender, userDTO.getGender());
        }
        // 更新年龄
        if (ObjectUtils.isEmpty(userDTO.getAge())) {
            jpaUpdateClause.set(qUser.age, userDTO.getAge());
        }

        jpaUpdateClause.where(qUser.userId.eq(userDTO.getUserId())).execute();
        if (ObjectUtils.isEmpty(userDTO.getEmail()) || ObjectUtils.isEmpty(userDTO.getMp()) || ObjectUtils.isEmpty(userDTO.getRole())) {
            AuthAccountBO authAccountBO = ConvertUtils.dtoConvertToBO(userDTO, AuthAccountBO.class);
            // 密码不允许在此处更新
            authAccountBO.setPassword(null);
            authAccountFeign.update(authAccountBO);
        }
    }

    private QBean<UserVO> getQBean(QUser qUser) {
        return Projections.bean(UserVO.class, qUser.userId, qUser.age, qUser.userType, qUser.state, qUser.avatar, qUser.gender, qUser.birthday, qUser.username);
    }
}
