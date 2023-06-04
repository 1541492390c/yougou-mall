package per.ccm.ygmall.biz.controller;

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
public class ResourceController {

    @Autowired
    private ResourceManager resourceManager;

    @PostMapping("/upload_avatar")
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
