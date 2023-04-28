package per.ccm.ygmall.user.service.impl;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import per.ccm.ygmall.common.service.BaseService;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.common.vo.PageVO;
import per.ccm.ygmall.user.dto.FeedbackDTO;
import per.ccm.ygmall.user.entity.Feedback;
import per.ccm.ygmall.user.entity.QFeedback;
import per.ccm.ygmall.user.entity.QFeedbackType;
import per.ccm.ygmall.user.repository.FeedbackRepository;
import per.ccm.ygmall.user.service.FeedbackService;
import per.ccm.ygmall.user.vo.FeedbackVO;

import java.util.List;

@Service
public class FeedbackServiceImpl extends BaseService implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public void save(FeedbackDTO feedbackDTO) {
        Feedback feedback = ConvertUtils.dtoConvertToEntity(feedbackDTO, Feedback.class);
        feedbackRepository.save(feedback);
    }

    @Override
    public PageVO<FeedbackVO> getFeedbackPages(Long userId, Pageable pageable) {
        QFeedback qFeedback = QFeedback.feedback;
        QFeedbackType qFeedbackType = QFeedbackType.feedbackType;
        QBean<FeedbackVO> qBean = this.getQBean(qFeedback, qFeedbackType);

        Predicate predicate = qFeedback.feedbackId.isNotNull();
        if (!ObjectUtils.isEmpty(userId)) {
            predicate = ExpressionUtils.and(predicate, qFeedback.userId.eq(userId));
        }

        Long total = feedbackRepository.count();
        List<FeedbackVO> feedbackList = super.jpaQueryFactory.select(qBean)
                .from(qFeedback).leftJoin(qFeedbackType)
                .on(qFeedback.feedbackId.eq(qFeedbackType.feedbackTypeId))
                .where(predicate).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
        return new PageVO<>(total, feedbackList);
    }

    private QBean<FeedbackVO> getQBean(QFeedback qFeedback, QFeedbackType qFeedbackType) {
        return Projections.bean(FeedbackVO.class, qFeedback.feedbackId, qFeedback.userId, qFeedback.feedbackTypeId,
                qFeedback.rate, qFeedback.contactWay, qFeedback.content, qFeedbackType.feedbackTypeName);
    }
}
