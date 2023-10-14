package per.ccm.ygmall.user.controller.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.basic.response.ResponseEntity;
import per.ccm.ygmall.common.basic.vo.PageVO;
import per.ccm.ygmall.common.security.util.SecurityContextUtils;
import per.ccm.ygmall.user.dto.FeedbackDTO;
import per.ccm.ygmall.user.entity.Feedback;
import per.ccm.ygmall.user.service.FeedbackService;
import per.ccm.ygmall.user.vo.FeedbackVO;

@RestController
@RequestMapping("/user/feedback")
@PreAuthorize("hasRole(@roleConfig.USER)")
@Tag(name = "用户反馈接口", description = "用户反馈接口")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/save")
    @Operation(summary = "保存用户反馈信息", description = "保存用户反馈信息")
    public ResponseEntity<Void> save(@RequestBody FeedbackDTO feedbackDTO) throws Exception {
        Long userId = SecurityContextUtils.getUserId();

        feedbackDTO.setUserId(userId);
        feedbackService.save(feedbackDTO);
        return ResponseEntity.success();
    }

    @GetMapping("/pages")
    @Operation(summary = "根据用户ID获取用户反馈信息分页列表", description = "根据用户ID获取用户反馈信息分页列表")
    @Parameters({
            @Parameter(name = "page_num", description = "当前页"),
            @Parameter(name = "page_size", description = "页数")})
    public ResponseEntity<PageVO<FeedbackVO>> getFeedbackPages(
            @RequestParam(value = "page_num", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) throws Exception {
        Long userId = SecurityContextUtils.getUserId();

        Page<Feedback> page = new Page<>(pageNum, pageSize);
        PageVO<FeedbackVO> pageVO = feedbackService.getFeedbackPages(userId, page);
        return ResponseEntity.success(pageVO);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除反馈信息", description = "传入用户反馈ID,删除反馈")
    public ResponseEntity<Void> delete(@PathVariable("id") Long feedbackId) {
        feedbackService.removeById(feedbackId);
        return ResponseEntity.success();
    }
}
