package top.zhangdashuai.oss.utils;

import com.aliyun.oss.OSS;
import com.github.f4b6a3.ulid.Ulid;
import jakarta.annotation.Resource;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

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
}
