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
import per.ccm.ygmall.biz.manager.ResourceManager;
import per.ccm.ygmall.common.response.ResponseEntity;

@RestController
@RequestMapping("/biz/resource")
@Tag(name = "资源接口", description = "资源接口")
public class ResourceController {

    @Autowired
    private ResourceManager resourceManager;

    @PostMapping("/upload")
    @Operation(summary = "上传文件", description = "上传文件")
    @Parameters({
            @Parameter(name = "resourceType", description = "资源类型"),
            @Parameter(name = "file", description = "文件")})
    public ResponseEntity<String> uploadAvatar(Integer resourceType, MultipartFile file) throws Exception {
        String avatarFileName = resourceManager.upload(resourceType, file);
        return ResponseEntity.success(avatarFileName);
    }
}
