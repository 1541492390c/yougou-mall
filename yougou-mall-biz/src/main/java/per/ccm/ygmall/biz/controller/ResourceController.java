package per.ccm.ygmall.biz.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import per.ccm.ygmall.common.response.ResponseEntity;
import per.ccm.ygmall.biz.manager.ResourceManager;
import per.ccm.ygmall.security.util.SecurityContextUtils;

import java.util.List;

@RestController
@RequestMapping("biz/resource")
@Tag(name = "资源接口", description = "资源接口")
public class ResourceController {

    @Autowired
    private ResourceManager resourceManager;

    @PostMapping("/upload_avatar")
    @Operation(summary = "上传头像", description = "传入原头像名称以及新头像文件")
    @Parameters({
            @Parameter(name = "oldAvatarFileName", description = "原头像文件名称"),
            @Parameter(name = "file", description = "新头像文件")
    })
    public ResponseEntity<String> uploadAvatar(String oldAvatarFileName, MultipartFile file) throws Exception {
        Long userId = SecurityContextUtils.getUserId();
        String avatarFileName = resourceManager.uploadAvatar(userId, oldAvatarFileName, file);
        return ResponseEntity.success(avatarFileName);
    }

    @PostMapping("/batch_upload")
    public ResponseEntity<String> batchUpload(Integer resourceType, List<MultipartFile> file) throws Exception {
        return null;
    }
}
