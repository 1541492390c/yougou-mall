package per.ccm.ygmall.resource.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import per.ccm.ygmall.api.user.bo.UserBO;
import per.ccm.ygmall.api.user.feign.UserFeign;
import per.ccm.ygmall.resource.enums.ResourceType;
import per.ccm.ygmall.resource.handler.MinioHandler;

@Component
public class ResourceManager {

    @Autowired
    private MinioHandler minioHandler;

    @Autowired
    private UserFeign userFeign;

    public String uploadAvatar(Long userId, String oldFileName, MultipartFile file) throws Exception {
        minioHandler.delete(oldFileName);
        String avatarFileName = minioHandler.upload(ResourceType.AVATAR, file);

        // 更新用户头像
        UserBO userBO = new UserBO();
        userBO.setUserId(userId);
        userBO.setAvatar(avatarFileName);
        userFeign.update(userBO);
        return avatarFileName;
    }

    public String upload(Integer resourceType, MultipartFile file) throws Exception {
        return minioHandler.upload(ResourceType.AVATAR, file);
    }
}
