package per.ccm.ygmall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.api.auth.bo.AuthAccountBO;
import per.ccm.ygmall.api.auth.feign.AuthAccountFeign;
import per.ccm.ygmall.cache.cache.CacheNames;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCode;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.database.util.ConvertUtils;
import per.ccm.ygmall.database.vo.PageVO;
import per.ccm.ygmall.user.dto.UserRegisterDTO;
import per.ccm.ygmall.user.dto.UserUpdateDTO;
import per.ccm.ygmall.user.entity.User;
import per.ccm.ygmall.user.mapper.UserMapper;
import per.ccm.ygmall.user.service.UserService;
import per.ccm.ygmall.user.vo.UserVO;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthAccountFeign authAccountFeign;

    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public void save(UserRegisterDTO userRegisterDTO) throws Exception {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        // 用户名已被使用
        if (userMapper.exists(queryWrapper.eq(User::getUsername, userRegisterDTO.getUsername()))) {
            throw new YougouException(ResponseCode.USER_ERROR_A00007);
        }
        User user = ConvertUtils.dtoConvertToEntity(userRegisterDTO, User.class);
        userMapper.insert(user);

        AuthAccountBO authAccountBO = ConvertUtils.dtoConvertToBO(userRegisterDTO, AuthAccountBO.class);
        authAccountBO.setUserId(user.getUserId());
        authAccountBO.setUsername(user.getUsername());
        // 抛异常回滚
        ResponseEntity<Void> response =authAccountFeign.save(authAccountBO);
        if (!ObjectUtils.nullSafeEquals(response.getCode(), ResponseCode.OK.value())) {
            throw new YougouException(ResponseCode.responseCodeOf(response.getCode()));
        }
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

        // 查询条件
        queryWrapper.eq(!ObjectUtils.isEmpty(userId), User::getUserId, userId).eq(!ObjectUtils.isEmpty(userType), User::getUserType, userType);
        IPage<User> pageInfo = userMapper.selectPage(page, queryWrapper);
        List<UserVO> userList = ConvertUtils.converList(pageInfo.getRecords(), UserVO.class);
        return new PageVO<>(pageInfo.getTotal(), userList);
    }

    @Override
    @CacheEvict(cacheNames = CacheNames.USERINFO_CACHE_NAME, key = "#userUpdateDTO.userId")
    @GlobalTransactional(rollbackFor = Exception.class)
    public void update(UserUpdateDTO userUpdateDTO) throws Exception {
        User user = ConvertUtils.dtoConvertToEntity(userUpdateDTO, User.class);
        userMapper.updateById(user);

        if (ObjectUtils.isEmpty(userUpdateDTO.getEmail()) || ObjectUtils.isEmpty(userUpdateDTO.getRole())) {
            AuthAccountBO authAccountBO = ConvertUtils.dtoConvertToBO(userUpdateDTO, AuthAccountBO.class);
            ResponseEntity<Void> response = authAccountFeign.update(authAccountBO);
            // 抛异常回滚
            if (!ObjectUtils.nullSafeEquals(response.getCode(), ResponseCode.OK.value())) {
                throw new YougouException(ResponseCode.responseCodeOf(response.getCode()));
            }
        }
    }
}
