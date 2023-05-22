package per.ccm.ygmall.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.ccm.ygmall.database.util.ConvertUtils;
import per.ccm.ygmall.database.vo.PageVO;
import per.ccm.ygmall.user.dto.FeedbackDTO;
import per.ccm.ygmall.user.entity.Feedback;
import per.ccm.ygmall.user.mapper.FeedbackMapper;
import per.ccm.ygmall.user.service.FeedbackService;
import per.ccm.ygmall.user.vo.FeedbackVO;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public void save(FeedbackDTO feedbackDTO) {
        Feedback feedback = ConvertUtils.dtoConvertToEntity(feedbackDTO, Feedback.class);
        feedbackMapper.insert(feedback);
    }

    @Override
    public PageVO<FeedbackVO> getFeedbackPages(Long userId, Page<Feedback> page) {
        IPage<FeedbackVO> pageInfo = feedbackMapper.selectFeedbackPages(userId, page);
        return new PageVO<>(pageInfo.getTotal(), pageInfo.getRecords());
    }
}
