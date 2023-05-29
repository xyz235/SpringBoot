package top.zhangdashuai.oss.pojo.req;

import lombok.Data;

import java.util.List;

/**
 * @author zhangdashuai
 */
@Data
public class FileBatchDelete {

    private List<String> keys;
}
