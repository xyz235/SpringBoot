package top.zhangdashuai.oss.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.github.f4b6a3.ulid.Ulid;
import jakarta.annotation.Resource;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author zhangdashuai
 */
@Component
public class OssUtil {

    @Resource
    private OSS ossClient;

    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.bucketName}")
    private String bucketName;

    @Value("${oss.dir}")
    private String dir;

    public String upload(MultipartFile file) {
        if (Objects.isNull(file)) {
            throw new RuntimeException("文件不能为空");
        }
        String dirAndNewFileName = dir + "/" + currentDate() + "/" + getAndNewFileName(file);
        try {
            ossClient.putObject(bucketName, dirAndNewFileName, file.getInputStream());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return "https://" + bucketName + "." + this.endpoint + "/" + dirAndNewFileName;
    }

    public void delete(String fileName) {
        try {
            ossClient.deleteObject(bucketName, fileName);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private String currentDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    private String getAndNewFileName(MultipartFile file) {
        String fileName = String.valueOf(Ulid.fast());
        String fileSuffix = FilenameUtils.getExtension(file.getOriginalFilename());
        return fileName + "." + fileSuffix;
    }

    public void batchDelete(List<String> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            return;
        }
        try {
            ossClient.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(null).withEncodingType("url"));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
