package per.ccm.ygmall.user.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.common.vo.PageVO;
import per.ccm.ygmall.user.entity.Feedback;
import per.ccm.ygmall.user.service.FeedbackService;
import per.ccm.ygmall.user.vo.FeedbackVO;

@RestController("adminFeedbackController")
@RequestMapping("/admin/user/feedback")
@PreAuthorize("hasAnyRole(@roleConfig.SUPER_ADMIN, @roleConfig.COMMON_ADMIN)")
@Tag(name = "用户反馈接口(管理员)", description = "用户反馈接口(管理员)")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/pages")
    @Operation(summary = "获取用户反馈信息分页列表", description = "获取用户反馈信息分页列表")
    @Parameters({
            @Parameter(name = "page_num", description = "当前页"),
            @Parameter(name = "page_size", description = "页数")})
    public ResponseEntity<PageVO<FeedbackVO>> getFeedbackPages(
            @RequestParam(value = "page_num", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) throws Exception {
        Page<Feedback> page = new Page<>(pageNum, pageSize);
        PageVO<FeedbackVO> pageVO = feedbackService.getFeedbackPages(null, page);
        return ResponseEntity.success(pageVO);
    }
}
