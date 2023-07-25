package per.ccm.ygmall.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCodeEnum;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.platform.dto.FeedbackTypeDTO;
import per.ccm.ygmall.platform.entity.FeedbackType;
import per.ccm.ygmall.platform.mapper.FeedbackTypeMapper;
import per.ccm.ygmall.platform.service.FeedbackTypeService;
import per.ccm.ygmall.platform.vo.FeedbackTypeVO;

import java.util.List;

@Service
public class FeedbackTypeServiceImpl extends ServiceImpl<FeedbackTypeMapper, FeedbackType> implements FeedbackTypeService {

    @Autowired
    private FeedbackTypeMapper feedbackTypeMapper;

    @Override
    public void save(FeedbackTypeDTO feedbackTypeDTO) {
        LambdaQueryWrapper<FeedbackType> queryWrapper = new LambdaQueryWrapper<>();

        // 判断用户反馈类型名称是否存在
        if (feedbackTypeMapper.exists(queryWrapper.eq(FeedbackType::getName, feedbackTypeDTO.getName()))) {
            throw new YougouException(ResponseCodeEnum.PLATFORM_ERROR_F1001);
        }
        FeedbackType feedbackType = ConvertUtils.convertProperties(feedbackTypeDTO, FeedbackType.class);
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
            throw new YougouException(ResponseCodeEnum.PLATFORM_ERROR_F1001);
        }
        FeedbackType feedbackType = ConvertUtils.convertProperties(feedbackTypeDTO, FeedbackType.class);
        feedbackTypeMapper.updateById(feedbackType);
    }
}
