package per.ccm.ygmall.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import per.ccm.ygmall.user.entity.Comment;
import per.ccm.ygmall.user.vo.RateStatisticsVO;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {
    RateStatisticsVO selectRateStatistics(Long productId);
}
