package top.zhangdashuai.unifiedexceptionhandling.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.zhangdashuai.unifiedexceptionhandling.enums.ReturnCode;

/**
 * @author zhangdashuai
 */
@RestControllerAdvice
public class ResultExceptionHandler {

    /**
     * 默认全局异常处理。
     *
     * @param e the e
     * @return ResultData
     */
    @ExceptionHandler(Exception.class)
    public ResultData<String> exception(Exception e) {
        return ResultData.fail(ReturnCode.RC_500.getCode(), e.getMessage());
    }
}
