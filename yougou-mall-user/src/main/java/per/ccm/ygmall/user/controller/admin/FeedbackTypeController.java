package per.ccm.ygmall.user.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.user.dto.FeedbackTypeDTO;
import per.ccm.ygmall.user.service.FeedbackTypeService;

@RestController("adminFeedbackTypeController")
@RequestMapping("/admin/user/feedback_type")
@PreAuthorize("hasAnyRole(@roleConfig.SUPER_ADMIN, @roleConfig.COMMON_ADMIN)")
@Tag(name = "用户反馈类型接口(管理员)", description = "用户反馈类型接口(管理员)")
public class FeedbackTypeController {

    @Autowired
    private FeedbackTypeService feedbackTypeService;

    @PostMapping("/save")
    @Operation(summary = "保存用户反馈类型", description = "保存用户反馈类型")
    public ResponseEntity<Void> save(@RequestBody FeedbackTypeDTO feedbackTypeDTO) throws Exception {
        feedbackTypeService.save(feedbackTypeDTO);
        return ResponseEntity.success();
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户反馈类型", description = "更新用户反馈类型")
    public ResponseEntity<Void> update(@RequestBody FeedbackTypeDTO feedbackTypeDTO) throws Exception {
        feedbackTypeService.update(feedbackTypeDTO);
        return ResponseEntity.success();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除用户反馈类型", description = "传入用户反馈类型ID删除反馈类型")
    public ResponseEntity<Void> delete(@RequestParam("feedback_type_id") Long feedbackTypeId) throws Exception {
        feedbackTypeService.delete(feedbackTypeId);
        return ResponseEntity.success();
    }
}
