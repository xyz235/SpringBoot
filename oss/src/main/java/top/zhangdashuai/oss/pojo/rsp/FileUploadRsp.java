package top.zhangdashuai.oss.pojo.rsp;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhangdashuai
 */
@Getter
@Setter
public class FileUploadRsp {

    private String ossUrl;

    private String originalFilename;
}
