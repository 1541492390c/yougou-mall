package per.ccm.ygmall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public void save(FeedbackDTO feedbackDTO) {
        Feedback feedback = ConvertUtils.convertProperties(feedbackDTO, Feedback.class);
        feedbackMapper.insert(feedback);
    }

    @Override
    public PageVO<FeedbackVO> getFeedbackPages(Long userId, Page<Feedback> page) {
        LambdaQueryWrapper<Feedback> queryWrapper = new LambdaQueryWrapper<>();
        IPage<Feedback> pageInfo = feedbackMapper.selectPage(page, queryWrapper.eq(Feedback::getUserId, userId));

        List<FeedbackVO> feedbackList = ConvertUtils.converList(pageInfo.getRecords(), FeedbackVO.class);
        return new PageVO<>(pageInfo.getTotal(), feedbackList);
    }
}
