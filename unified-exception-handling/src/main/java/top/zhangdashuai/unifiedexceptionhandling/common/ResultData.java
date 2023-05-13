package top.zhangdashuai.unifiedexceptionhandling.common;

import lombok.Data;
import top.zhangdashuai.unifiedexceptionhandling.enums.ReturnCode;

/**
 * @author zhangdashuai
 */
@Data
public class ResultData<T> {
    private Integer code;
    private String message;
    private T data;
    private Long timestamp;

    public static <T> ResultData<T> success(T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(ReturnCode.RC_200.getCode());
        resultData.setMessage(ReturnCode.RC_200.getMessage());
        resultData.setData(data);
        return resultData;
    }

    public static <T> ResultData<T> fail(Integer code, String message) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(code);
        resultData.setMessage(message);
        return resultData;
    }
}
