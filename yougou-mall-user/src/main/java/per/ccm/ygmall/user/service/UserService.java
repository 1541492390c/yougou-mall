package per.ccm.ygmall.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.domain.Pageable;
import per.ccm.ygmall.common.vo.PageVO;
import per.ccm.ygmall.user.dto.UserDTO;
import per.ccm.ygmall.user.entity.User;
import per.ccm.ygmall.user.vo.UserVO;

/**
 * 用户服务
 * */
public interface UserService {
    /**
     * 保存用户信息
     *
     * @param userDTO 用户信息传输数据
     * */
    void save(UserDTO userDTO) throws Exception;

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
     * @param userDTO 用户传输数据
     * */
    void update(UserDTO userDTO) throws Exception;
}
