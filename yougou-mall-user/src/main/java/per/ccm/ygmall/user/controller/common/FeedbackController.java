package per.ccm.ygmall.user.controller.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.controller.BaseController;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.common.vo.PageVO;
import per.ccm.ygmall.security.util.SecurityUtils;
import per.ccm.ygmall.user.dto.FeedbackDTO;
import per.ccm.ygmall.user.entity.Feedback;
import per.ccm.ygmall.user.service.FeedbackService;
import per.ccm.ygmall.user.vo.FeedbackVO;

@RestController
@RequestMapping("/user/feedback")
@Tag(name = "用户反馈接口", description = "用户反馈接口")
public class FeedbackController extends BaseController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/save")
    @PreAuthorize("hasRole(@roleConfig.USER)")
    @Operation(summary = "保存用户反馈信息", description = "保存用户反馈信息")
    public ResponseEntity<Void> save(@RequestBody FeedbackDTO feedbackDTO) throws Exception {
        feedbackService.save(feedbackDTO);
        return ResponseEntity.success();
    }

    @GetMapping("/pages")
    @PreAuthorize("hasRole(@roleConfig.USER)")
    @Operation(summary = "根据用户ID获取用户反馈信息分页列表", description = "根据用户ID获取用户反馈信息分页列表")
    public ResponseEntity<PageVO<FeedbackVO>> getFeedbackPages(
            @RequestParam(value = "page_num", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) throws Exception {
        Long userId = SecurityUtils.getUserId();

        Page<Feedback> page = new Page<>(pageNum, pageSize);
        PageVO<FeedbackVO> pageVO = feedbackService.getFeedbackPages(userId, page);
        return ResponseEntity.success(pageVO);
    }
}
