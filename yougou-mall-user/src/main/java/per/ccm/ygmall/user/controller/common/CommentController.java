package per.ccm.ygmall.user.controller.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.database.vo.PageVO;
import per.ccm.ygmall.security.util.SecurityContextUtils;
import per.ccm.ygmall.user.dto.CommentDTO;
import per.ccm.ygmall.user.entity.Comment;
import per.ccm.ygmall.user.service.CommentService;
import per.ccm.ygmall.user.vo.CommentVO;
import per.ccm.ygmall.user.vo.RateStatisticsVO;

@RestController
@RequestMapping("/user/comment")
@Tag(name = "评论接口", description = "评论接口")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/save")
    @PreAuthorize("hasRole(@roleConfig.USER)")
    @Operation(summary = "保存评论", description = "保存评论")
    public ResponseEntity<Void> save(@RequestBody CommentDTO commentDTO) throws Exception {
        Long userId = SecurityContextUtils.getUserId();

        commentDTO.setUserId(userId);
        commentService.save(commentDTO);
        return ResponseEntity.success();
    }

    @GetMapping("/rate_statistics")
    @Operation(summary = "获取商品评分统计信息", description = "获取商品评分统计信息")
    @Parameter(name = "product_id", description = "商品ID")
    public ResponseEntity<RateStatisticsVO> getRateStatistics(@RequestParam("product_id") Long productId) throws Exception {
        RateStatisticsVO rateStatistics = commentService.getRateStatistics(productId);
        return ResponseEntity.success(rateStatistics);
    }

    @GetMapping("/pages")
    @Operation(summary = "获取评论分页信息", description = "获取评论分页信息")
    @Parameters({
            @Parameter(name = "page_num", description = "当前页"),
            @Parameter(name = "page_size", description = "页数"),
            @Parameter(name = "user_id", description = "用户ID"),
            @Parameter(name = "product_id", description = "商品ID")})
    public ResponseEntity<PageVO<CommentVO>> getCommentPages(
            @RequestParam(name = "page_num", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "page_size", defaultValue = "10") Integer pageSize,
            @RequestParam(name = "user_id", required = false) Long userId,
            @RequestParam(name = "product_id", required = false) Long productId) throws Exception {
        Page<Comment> page = new Page<>(pageNum, pageSize);
        PageVO<CommentVO> pageVO = commentService.getCommentPages(userId, productId, page);
        return ResponseEntity.success(pageVO);
    }
}
