package per.ccm.ygmall.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import per.ccm.ygmall.user.entity.User;
import per.ccm.ygmall.user.vo.UserVO;

@Repository
public interface UserMapper extends BaseMapper<User> {
    IPage<UserVO> selectUserPages(@Param("userId") Long userId, @Param("userType") Integer userType, Page<User> page);
}
