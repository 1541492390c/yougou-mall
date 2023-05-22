package per.ccm.ygmall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCode;
import per.ccm.ygmall.database.util.ConvertUtils;
import per.ccm.ygmall.user.dto.FeedbackTypeDTO;
import per.ccm.ygmall.user.entity.FeedbackType;
import per.ccm.ygmall.user.mapper.FeedbackTypeMapper;
import per.ccm.ygmall.user.service.FeedbackTypeService;
import per.ccm.ygmall.user.vo.FeedbackTypeVO;

import java.util.List;

@Service
public class FeedbackTypeServiceImpl implements FeedbackTypeService {

    @Autowired
    private FeedbackTypeMapper feedbackTypeMapper;

    @Override
    public void save(FeedbackTypeDTO feedbackTypeDTO) {
        LambdaQueryWrapper<FeedbackType> queryWrapper = new LambdaQueryWrapper<>();

        // 判断用户反馈类型名称是否存在
        if (feedbackTypeMapper.exists(queryWrapper.eq(FeedbackType::getName, feedbackTypeDTO.getName()))) {
            throw new YougouException(ResponseCode.USER_ERROR_A10001);
        }
        FeedbackType feedbackType = ConvertUtils.dtoConvertToEntity(feedbackTypeDTO, FeedbackType.class);
        feedbackTypeMapper.insert(feedbackType);
    }

    @Override
    public List<FeedbackTypeVO> getFeedbackTypeList() {
        LambdaQueryWrapper<FeedbackType> queryWrapper = new LambdaQueryWrapper<>();
        List<FeedbackType> feedbackTypeList = feedbackTypeMapper.selectList(queryWrapper);
        return ConvertUtils.converList(feedbackTypeList, FeedbackTypeVO.class);
    }

    @Override
    public void update(FeedbackTypeDTO feedbackTypeDTO) {
        LambdaQueryWrapper<FeedbackType> queryWrapper = new LambdaQueryWrapper<>();

        // 判断用户反馈类型名称是否存在
        if (feedbackTypeMapper.exists(queryWrapper.eq(FeedbackType::getName, feedbackTypeDTO.getName()))) {
            throw new YougouException(ResponseCode.USER_ERROR_A10001);
        }
        FeedbackType feedbackType = ConvertUtils.dtoConvertToEntity(feedbackTypeDTO, FeedbackType.class);
        feedbackTypeMapper.updateById(feedbackType);
    }

    @Override
    public void delete(Long feedbackTypeId) {
        LambdaQueryWrapper<FeedbackType> queryWrapper = new LambdaQueryWrapper<>();
        feedbackTypeMapper.delete(queryWrapper.eq(FeedbackType::getFeedbackTypeId, feedbackTypeId));
    }
}
