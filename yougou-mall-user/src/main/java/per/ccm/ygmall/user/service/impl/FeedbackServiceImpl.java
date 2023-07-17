package per.ccm.ygmall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.database.vo.PageVO;
import per.ccm.ygmall.user.dto.FeedbackDTO;
import per.ccm.ygmall.user.entity.Feedback;
import per.ccm.ygmall.user.mapper.FeedbackMapper;
import per.ccm.ygmall.user.service.FeedbackService;
import per.ccm.ygmall.user.vo.FeedbackVO;

import java.util.List;

@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public void save(FeedbackDTO feedbackDTO) {
        Feedback feedback = ConvertUtils.convertProperties(feedbackDTO, Feedback.class);
        feedbackMapper.insert(feedback);
    }

    @Override
    public PageVO<FeedbackVO> getFeedbackPages(Long userId, Page<Feedback> page) {
        Page<Feedback> pageInfo = feedbackMapper.selectPage(page, new LambdaQueryWrapper<Feedback>().eq(Feedback::getUserId, userId));
        List<FeedbackVO> feedbackList = ConvertUtils.converList(pageInfo.getRecords(), FeedbackVO.class);
        return new PageVO<>(pageInfo.getTotal(), feedbackList);
    }
}
