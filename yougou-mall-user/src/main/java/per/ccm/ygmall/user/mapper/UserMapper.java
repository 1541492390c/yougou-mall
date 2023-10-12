package per.ccm.ygmall.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import per.ccm.ygmall.user.entity.User;
import per.ccm.ygmall.user.vo.UserStatisticsVO;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {
    List<UserStatisticsVO> selectUserStatistics();
}
