package per.ccm.ygmall.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import per.ccm.ygmall.common.basic.vo.PageVO;
import per.ccm.ygmall.user.dto.CommentDTO;
import per.ccm.ygmall.user.entity.Comment;
import per.ccm.ygmall.user.vo.CommentVO;
import per.ccm.ygmall.user.vo.RateStatisticsVO;

public interface CommentService extends IService<Comment> {
    /**
     * 保存评价信息
     *
     * @param commentDTO 评价传输数据
     * */
    void save(CommentDTO commentDTO) throws Exception;
    /**
     * 获取商品评分统计信息
     *
     * @param productId 商品ID
     * @return 商品评分统计信息
     * */
    RateStatisticsVO getRateStatistics(Long productId) throws Exception;

    /**
     * 获取评价分页信息
     *
     * @param userId 用户ID
     * @param productId 商品ID
     * @param page 分页信息
     * @return 评价分页信息
     * */
    PageVO<CommentVO> getCommentPages(Long userId, Long productId, Page<Comment> page) throws Exception;
}
