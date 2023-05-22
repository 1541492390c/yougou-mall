package per.ccm.ygmall.user.service;

import per.ccm.ygmall.user.dto.FeedbackTypeDTO;
import per.ccm.ygmall.user.vo.FeedbackTypeVO;

import java.util.List;

/**
 * 用户反馈类型服务
 * */
public interface FeedbackTypeService {

    /**
     * 保存用户反馈类型
     *
     * @param feedbackTypeDTO 用户反馈类型传输数据
     * */
    void save(FeedbackTypeDTO feedbackTypeDTO) throws Exception;

    /**
     * 获取所有用户反馈分类信息
     * */
    List<FeedbackTypeVO> getFeedbackTypeList() throws Exception;

    /**
     * 更新用户反馈类型
     *
     * @param feedbackTypeDTO 用户反馈类型传输数据
     * */
    void update(FeedbackTypeDTO feedbackTypeDTO) throws Exception;

    /**
     * 删除用户反馈类型
     *
     * @param feedbackTypeId 用户反馈类型ID
     * */
    void delete(Long feedbackTypeId) throws Exception;
}
