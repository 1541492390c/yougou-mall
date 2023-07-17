package per.ccm.ygmall.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import per.ccm.ygmall.user.entity.Comment;
import per.ccm.ygmall.user.vo.CommentVO;
import per.ccm.ygmall.user.vo.RateStatisticsVO;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {
    RateStatisticsVO selectRateStatistics(Long productId);

    Page<CommentVO> selectCommentPages(@Param("userId") Long userId, @Param("productId") Long productId, Page<Comment> page);
}
