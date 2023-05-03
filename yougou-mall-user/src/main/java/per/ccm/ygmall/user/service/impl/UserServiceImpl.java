package per.ccm.ygmall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
import per.ccm.ygmall.user.entity.User;
import per.ccm.ygmall.user.mapper.UserMapper;
import per.ccm.ygmall.user.service.UserService;
import per.ccm.ygmall.user.vo.UserVO;

import java.util.List;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthAccountFeign authAccountFeign;

    @Override
    public void save(UserDTO userDTO) throws Exception {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        // 判断用户名是否存在
        if (userMapper.exists(queryWrapper.eq(User::getUsername, userDTO.getUsername()))) {
            throw new YougouException(ResponseCode.USER_ERROR_A00007);
        }
        User user = ConvertUtils.dtoConvertToEntity(userDTO, User.class);
        userMapper.insert(user);

        AuthAccountBO authAccountBO = ConvertUtils.dtoConvertToBO(userDTO, AuthAccountBO.class);
        authAccountBO.setUserId(user.getUserId());
        authAccountBO.setUsername(user.getUsername());
        authAccountFeign.save(authAccountBO);
    }

    @Override
    @Cacheable(cacheNames = CacheNames.USERINFO_CACHE_NAME, key = "#userId", sync = true)
    public UserVO getUserinfo(Long userId) {
        User user = userMapper.selectById(userId);
        return ConvertUtils.entityConvertToVO(user, UserVO.class);
    }

    @Override
    public PageVO<UserVO> getUserPages(Long userId, Integer userType, Page<User> page) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        if (!ObjectUtils.isEmpty(userId)) {
            queryWrapper.eq(User::getUserId, userId);
        }
        if (!ObjectUtils.isEmpty(userType)) {
            queryWrapper.eq(User::getUserType, userType);
        }
        IPage<User> pageInfo = userMapper.selectPage(page, queryWrapper);
        List<UserVO> userList = ConvertUtils.converList(pageInfo.getRecords(), UserVO.class);
        return new PageVO<>(pageInfo.getTotal(), userList);
    }

    @Override
    @CacheEvict(cacheNames = CacheNames.USERINFO_CACHE_NAME, key = "#userDTO.userId")
    public void update(UserDTO userDTO) throws Exception {
        User user = ConvertUtils.dtoConvertToEntity(userDTO, User.class);
        userMapper.updateById(user);

        if (ObjectUtils.isEmpty(userDTO.getEmail()) || ObjectUtils.isEmpty(userDTO.getMp()) || ObjectUtils.isEmpty(userDTO.getRole())) {
            AuthAccountBO authAccountBO = ConvertUtils.dtoConvertToBO(userDTO, AuthAccountBO.class);
            // 密码不允许在此处更新
            authAccountBO.setPassword(null);
            authAccountFeign.update(authAccountBO);
        }
    }
}
