package per.ccm.ygmall.biz.handler;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import per.ccm.ygmall.common.exception.ServerException;
import per.ccm.ygmall.biz.enums.ResourceType;
import per.ccm.ygmall.common.util.RandomUtils;

@Component
public class MinioHandler {

    @Value("${minio.bucket}")
    private String bucket;

    @Autowired
    private MinioClient minioClient;

    /**
     * 上传文件
     *
     * @param resourceType 资源文件类型
     * @param file 上传文件
     * @return 保存的文件地址
     * */
    public String upload(ResourceType resourceType, MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        // 文件名为空抛异常
        if (ObjectUtils.isEmpty(originalFilename)) {
            throw new ServerException("MinioHandler-->upload error, originFilename is empty");
        }
        // 获取文件扩展名
        String fileSuffix = originalFilename.substring(file.getOriginalFilename().indexOf('.'));
        // 产生新的文件名称
        String fileName = RandomUtils.randomUUID() + fileSuffix;
        String fileObject = resourceType.getPath() + fileName;
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(bucket)
                .object(fileObject)
                .stream(file.getInputStream(), file.getSize(), -1)
                .build();
        minioClient.putObject(args);
        // 上传成功后返回文件地址
        return fileName;
    }

    /**
     * 删除文件
     *
     * @param objectName 删除的文件地址
     * */
    public void delete(String objectName) throws Exception {
        RemoveObjectArgs args = RemoveObjectArgs.builder()
                .bucket(bucket)
                .object(objectName)
                .build();
        minioClient.removeObject(args);
    }
}