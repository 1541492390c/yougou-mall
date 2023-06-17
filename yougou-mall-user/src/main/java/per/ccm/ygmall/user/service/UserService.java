package per.ccm.ygmall.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import per.ccm.ygmall.database.vo.PageVO;
import per.ccm.ygmall.user.dto.UserRegisterDTO;
import per.ccm.ygmall.user.dto.UserUpdateDTO;
import per.ccm.ygmall.user.entity.User;
import per.ccm.ygmall.user.vo.UserVO;

/**
 * 用户服务
 * */
public interface UserService {
    /**
     * 保存用户信息
     *
     * @param userRegisterDTO 用户注册信息传输数据
     * */
    void save(UserRegisterDTO userRegisterDTO) throws Exception;

    /**
     * 根据用户id获取用户信息
     *
     * @param userId 用户id
     * @return 用户信息
     * */
    UserVO getUserinfo(Long userId) throws Exception;

    /**
     * 分页获取用户信息列表
     *
     * @param userId 用户ID
     * @param useType 用户类型
     * @param page 分页信息
     * @return 用户分页信息
     * */
    PageVO<UserVO> getUserPages(Long userId, Integer useType, Page<User> page) throws Exception;

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
