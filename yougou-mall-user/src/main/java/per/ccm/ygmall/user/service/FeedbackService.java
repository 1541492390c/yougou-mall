package per.ccm.ygmall.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import per.ccm.ygmall.common.vo.PageVO;
import per.ccm.ygmall.user.dto.FeedbackDTO;
import per.ccm.ygmall.user.entity.Feedback;
import per.ccm.ygmall.user.vo.FeedbackVO;

/**
 * 用户反馈服务
 * */
public interface FeedbackService {
    /**
     * 保存用户反馈信息
     *
     * @param feedbackDTO 用户反馈传输数据
     * */
    void  save(FeedbackDTO feedbackDTO) throws Exception;

    /**
     * 分页获取用户反馈信息列表
     *
     * @param userId 用户ID
     * @param page 分页信息
     * @return 用户反馈分页信息
     * */
    PageVO<FeedbackVO> getFeedbackPages(Long userId, Page<Feedback> page) throws Exception;
}
