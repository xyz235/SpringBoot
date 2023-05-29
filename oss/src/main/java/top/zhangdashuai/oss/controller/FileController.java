package top.zhangdashuai.oss.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.zhangdashuai.oss.pojo.req.FileBatchDelete;
import top.zhangdashuai.oss.pojo.rsp.FileUploadRsp;
import top.zhangdashuai.oss.utils.OssUtil;

/**
 * @author zhangdashuai
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private OssUtil ossUtil;

    @PostMapping("/upload")
    public FileUploadRsp upload(MultipartFile file) {
        FileUploadRsp rsp = new FileUploadRsp();
        rsp.setOriginalFilename(file.getOriginalFilename());
        rsp.setOssUrl(ossUtil.upload(file));
        return rsp;
    }

    @PostMapping("/delete")
    public void delete(String fileName) {
        ossUtil.delete(fileName);
    }

    @PostMapping("/batchDelete")
    public void batchDelete(@RequestBody FileBatchDelete fileBatchDelete) {
        ossUtil.batchDelete(fileBatchDelete.getKeys());
    }
}
