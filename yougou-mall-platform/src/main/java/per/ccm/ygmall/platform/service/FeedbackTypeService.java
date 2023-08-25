package per.ccm.ygmall.platform.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import per.ccm.ygmall.common.basic.vo.PageVO;
import per.ccm.ygmall.platform.dto.FeedbackTypeDTO;
import per.ccm.ygmall.platform.entity.FeedbackType;
import per.ccm.ygmall.platform.vo.FeedbackTypeVO;

import java.util.List;

/**
 * 用户反馈类型服务
 * */
public interface FeedbackTypeService extends IService<FeedbackType> {

    /**
     * 保存用户反馈类型
     *
     * @param feedbackTypeDTO 用户反馈类型传输数据
     * */
    void save(FeedbackTypeDTO feedbackTypeDTO) throws Exception;

    /**
     * 分页获取反馈类型图信息
     *
     * @param page 分页信息
     * @return 反馈类型分页信息
     * */
    PageVO<FeedbackTypeVO> getFeedbackTypePages(Page<FeedbackType> page) throws Exception;

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
}
