package per.ccm.ygmall.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.ccm.ygmall.user.mapper.CommentMapper;
import per.ccm.ygmall.user.service.CommentService;
import per.ccm.ygmall.user.vo.RateStatisticsVO;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public RateStatisticsVO getRateStatistics(Long productId) {
        return commentMapper.selectRateStatistics(productId);
    }
}
