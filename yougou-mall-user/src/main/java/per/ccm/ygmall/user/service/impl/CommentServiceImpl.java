package per.ccm.ygmall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.ccm.ygmall.common.exception.YougouException;
import per.ccm.ygmall.common.response.ResponseCodeEnum;
import per.ccm.ygmall.common.util.ConvertUtils;
import per.ccm.ygmall.database.vo.PageVO;
import per.ccm.ygmall.user.dto.CommentDTO;
import per.ccm.ygmall.user.entity.Comment;
import per.ccm.ygmall.user.mapper.CommentMapper;
import per.ccm.ygmall.user.service.CommentService;
import per.ccm.ygmall.user.vo.CommentVO;
import per.ccm.ygmall.user.vo.RateStatisticsVO;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void save(CommentDTO commentDTO) throws Exception {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        // 判断该订单是否已经评价
        queryWrapper.eq(Comment::getOrderId, commentDTO.getOrderId()).eq(Comment::getOrderItemId, commentDTO.getOrderItemId());
        if (commentMapper.exists(queryWrapper)) {
            throw new YougouException(ResponseCodeEnum.USER_ERROR_A3001);
        }
        Comment comment = ConvertUtils.convertProperties(commentDTO, Comment.class);
        commentMapper.insert(comment);
    }

    @Override
    public RateStatisticsVO getRateStatistics(Long productId) {
        return commentMapper.selectRateStatistics(productId);
    }

    @Override
    public PageVO<CommentVO> getCommentPages(Long userId, Long productId, Page<Comment> page) {
        Page<CommentVO> pageInfo = commentMapper.selectCommentPages(userId, productId, page);
        return new PageVO<>(pageInfo.getTotal(), pageInfo.getRecords());
    }
}
