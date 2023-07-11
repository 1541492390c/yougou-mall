package per.ccm.ygmall.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.ccm.ygmall.user.entity.Comment;
import per.ccm.ygmall.user.mapper.CommentMapper;
import per.ccm.ygmall.user.service.CommentService;
import per.ccm.ygmall.user.vo.RateStatisticsVO;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public RateStatisticsVO getRateStatistics(Long productId) {
        return commentMapper.selectRateStatistics(productId);
    }
}
