package per.ccm.ygmall.user.controller.common;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.user.service.FeedbackTypeService;
import per.ccm.ygmall.user.vo.FeedbackTypeVO;

import java.util.List;

@RestController
@RequestMapping("/user/feedback_type")
@Tag(name = "用户反馈类型接口", description = "用户反馈类型接口")
public class FeedbackTypeController {

    @Autowired
    private FeedbackTypeService feedbackTypeService;

    @GetMapping("/list")
    @Operation(summary = "获取所有用户反馈类型信息", description = "获取所有用户反馈类型信息")
    public ResponseEntity<List<FeedbackTypeVO>> getFeedbackTypeList() throws Exception {
        List<FeedbackTypeVO> feedbackTypeList = feedbackTypeService.getFeedbackTypeList();
        return ResponseEntity.success(feedbackTypeList);
    }
}
