package per.ccm.ygmall.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;
import per.ccm.ygmall.user.entity.Feedback;
import per.ccm.ygmall.user.vo.FeedbackVO;

@Repository
public interface FeedbackMapper extends BaseMapper<Feedback> {
    IPage<FeedbackVO> selectFeedbackPages(Long userId, Page<Feedback> page);
}
