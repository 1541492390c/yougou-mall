package per.ccm.ygmall.platform.controller.admin;

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
import per.ccm.ygmall.platform.dto.FeedbackTypeDTO;
import per.ccm.ygmall.platform.entity.FeedbackType;
import per.ccm.ygmall.platform.service.FeedbackTypeService;
import per.ccm.ygmall.platform.vo.FeedbackTypeVO;

@RestController("adminFeedbackTypeController")
@RequestMapping("/admin/platform/feedback_type")
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

    @GetMapping("/pages")
    @Operation(summary = "分页获取反馈类型信息", description = "分页获取反馈类型信息")
    @Parameters({
            @Parameter(name = "pageNum", description = "当前页"),
            @Parameter(name = "pageSize", description = "页数")
    })
    public ResponseEntity<PageVO<FeedbackTypeVO>> getBannerPages(
            @RequestParam(value = "page_num", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) throws Exception {
        Page<FeedbackType> page = new Page<>(pageNum, pageSize);
        PageVO<FeedbackTypeVO> pageVO = feedbackTypeService.getFeedbackTypePages(page);
        return ResponseEntity.success(pageVO);
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户反馈类型", description = "更新用户反馈类型")
    public ResponseEntity<Void> update(@RequestBody FeedbackTypeDTO feedbackTypeDTO) throws Exception {
        feedbackTypeService.update(feedbackTypeDTO);
        return ResponseEntity.success();
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole(@roleConfig.SUPER_ADMIN)")
    @Operation(summary = "删除用户反馈类型", description = "传入用户反馈类型ID删除反馈类型")
    @Parameter(name = "feedback_type_id", description = "用户反馈类型ID", required = true)
    public ResponseEntity<Void> delete(@RequestParam("feedback_type_id") Long feedbackTypeId) {
        feedbackTypeService.removeById(feedbackTypeId);
        return ResponseEntity.success();
    }
}
