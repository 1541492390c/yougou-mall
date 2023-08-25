package per.ccm.ygmall.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.api.auth.bo.AuthAccountBO;
import per.ccm.ygmall.api.auth.feign.AuthAccountFeign;
import per.ccm.ygmall.common.cache.cache.CacheNames;
import per.ccm.ygmall.common.basic.exception.YougouException;
import per.ccm.ygmall.common.basic.response.ResponseCodeEnum;
import per.ccm.ygmall.common.basic.util.ConvertUtils;
import per.ccm.ygmall.user.dto.UserRegisterDTO;
import per.ccm.ygmall.user.dto.UserUpdateDTO;
import per.ccm.ygmall.user.entity.User;
import per.ccm.ygmall.user.mapper.UserMapper;
import per.ccm.ygmall.user.service.UserService;
import per.ccm.ygmall.user.vo.UserVO;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthAccountFeign authAccountFeign;

    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public void save(UserRegisterDTO userRegisterDTO) throws Exception {
        User user = ConvertUtils.convertProperties(userRegisterDTO, User.class);
        userMapper.insert(user);

        AuthAccountBO authAccountBO = ConvertUtils.convertProperties(userRegisterDTO, AuthAccountBO.class);
        authAccountBO.setUserId(user.getUserId());
        // 抛异常回滚
        if (!authAccountFeign.save(authAccountBO).responseSuccess()) {
            throw new YougouException(ResponseCodeEnum.SERVER_ERROR_00001);
        }
    }

    @Override
    @Cacheable(cacheNames = CacheNames.USERINFO_CACHE_NAME, key = "#userId", sync = true)
    public UserVO getUserinfo(Long userId) {
        User user = userMapper.selectById(userId);
        return ConvertUtils.convertProperties(user, UserVO.class);
    }

    @Override
    @CacheEvict(cacheNames = CacheNames.USERINFO_CACHE_NAME, key = "#userUpdateDTO.userId")
    @GlobalTransactional(rollbackFor = Exception.class)
    public void update(UserUpdateDTO userUpdateDTO) throws Exception {
        User user = ConvertUtils.convertProperties(userUpdateDTO, User.class);
        userMapper.updateById(user);

        if (ObjectUtils.isEmpty(userUpdateDTO.getEmail()) || ObjectUtils.isEmpty(userUpdateDTO.getRole())) {
            AuthAccountBO authAccountBO = ConvertUtils.convertProperties(userUpdateDTO, AuthAccountBO.class);
            // 抛异常回滚
            if (!authAccountFeign.update(authAccountBO).responseSuccess()) {
                throw new YougouException(ResponseCodeEnum.SERVER_ERROR_00001);
            }
        }
    }

    @Override
    @CacheEvict(cacheNames = CacheNames.USERINFO_CACHE_NAME, key = "#userId")
    public void removeUserinfoCache(Long userId) {
    }
}
