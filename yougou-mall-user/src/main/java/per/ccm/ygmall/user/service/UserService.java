package per.ccm.ygmall.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import per.ccm.ygmall.common.basic.vo.PageVO;
import per.ccm.ygmall.user.dto.UserRegisterDTO;
import per.ccm.ygmall.user.dto.UserUpdateDTO;
import per.ccm.ygmall.user.entity.User;
import per.ccm.ygmall.user.vo.UserVO;

/**
 * 用户服务
 * */
public interface UserService extends IService<User> {
    /**
     * 保存用户信息
     *
     * @param userRegisterDTO 用户注册信息传输数据
     * */
    void save(UserRegisterDTO userRegisterDTO) throws Exception;

    /**
     * 获取用户分页信息
     *
     * @param userType 用户类型
     * @param gender 性别
     * @param state 用户状态
     * @param nickname 昵称
     * @param page 分页
     * @return 用户分页信息
     * */
    PageVO<UserVO> getUserPages(Integer userType, Integer gender, Integer state, String nickname, Page<User> page) throws Exception;

    /**
     * 根据用户id获取用户信息
     *
     * @param userId 用户id
     * @return 用户信息
     * */
    UserVO getUserinfo(Long userId) throws Exception;

    /**
     * 更新用户信息
     *
     * @param userUpdateDTO 用户更新传输数据
     * */
    void update(UserUpdateDTO userUpdateDTO) throws Exception;

    /**
     * 移除用户信息
     *
     * @param userId 用户ID
     * */
    void removeUserinfoCache(Long userId) throws Exception;
}
