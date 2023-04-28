package per.ccm.ygmall.user.service.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCode;
import per.ccm.ygmall.common.service.BaseService;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.user.dto.FeedbackTypeDTO;
import per.ccm.ygmall.user.entity.FeedbackType;
import per.ccm.ygmall.user.entity.QFeedbackType;
import per.ccm.ygmall.user.repository.FeedbackTypeRepository;
import per.ccm.ygmall.user.service.FeedbackTypeService;
import per.ccm.ygmall.user.vo.FeedbackTypeVO;

import java.util.List;

@Service
public class FeedbackTypeServiceImpl extends BaseService implements FeedbackTypeService {

    @Autowired
    private FeedbackTypeRepository feedbackTypeRepository;

    @Override
    public void save(FeedbackTypeDTO feedbackTypeDTO) {
        QFeedbackType qFeedbackType = QFeedbackType.feedbackType;

        // 判断用户反馈类型名称是否存在
        if (feedbackTypeRepository.exists(qFeedbackType.feedbackTypeName.eq(feedbackTypeDTO.getFeedbackTypeName()))) {
            throw new YougouException(ResponseCode.USER_ERROR_A10001);
        }
        FeedbackType feedbackType = ConvertUtils.dtoConvertToEntity(feedbackTypeDTO, FeedbackType.class);
        feedbackTypeRepository.save(feedbackType);
    }

    @Override
    public List<FeedbackTypeVO> getFeedbackTypeList() {
        QFeedbackType qFeedbackType = QFeedbackType.feedbackType;
        QBean<FeedbackTypeVO> qBean = this.getQBean(qFeedbackType);

        return super.jpaQueryFactory.select(qBean).from(qFeedbackType).fetch();
    }

    @Override
    public void update(FeedbackTypeDTO feedbackTypeDTO) {
        QFeedbackType qFeedbackType = QFeedbackType.feedbackType;

        // 判断用户反馈类型名称是否存在
        if (feedbackTypeRepository.exists(qFeedbackType.feedbackTypeName.eq(feedbackTypeDTO.getFeedbackTypeName()))) {
            throw new YougouException(ResponseCode.USER_ERROR_A10001);
        }
        super.jpaQueryFactory.update(qFeedbackType)
                .set(qFeedbackType.feedbackTypeName, feedbackTypeDTO.getFeedbackTypeName())
                .where(qFeedbackType.feedbackTypeId.eq(feedbackTypeDTO.getFeedbackTypeId()))
                .execute();
    }

    @Override
    public void delete(Long feedbackTypeId) {
        feedbackTypeRepository.deleteById(feedbackTypeId);
    }

    private QBean<FeedbackTypeVO> getQBean(QFeedbackType qFeedbackType) {
        return Projections.bean(FeedbackTypeVO.class, qFeedbackType.feedbackTypeId, qFeedbackType.feedbackTypeName);
    }
}
