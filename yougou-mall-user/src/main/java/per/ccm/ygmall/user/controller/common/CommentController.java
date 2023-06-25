package per.ccm.ygmall.user.controller.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.user.service.CommentService;
import per.ccm.ygmall.user.vo.RateStatisticsVO;

@RestController
@RequestMapping("/user/comment")
@Tag(name = "评论接口", description = "评论接口")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/rate_statistics")
    @Operation(summary = "获取商品评分统计信息", description = "获取商品评分统计信息")
    @Parameter(name = "product_id", description = "商品ID")
    public ResponseEntity<RateStatisticsVO> getRateStatistics(@RequestParam("product_id") Long productId) throws Exception {
        RateStatisticsVO rateStatistics = commentService.getRateStatistics(productId);
        return ResponseEntity.success(rateStatistics);
    }
}
