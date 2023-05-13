package top.zhangdashuai.unifiedexceptionhandling.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhangdashuai
 */
@Getter
@AllArgsConstructor
public enum ReturnCode {

    /**
     * 操作成功
     */
    RC_200(200, "操作成功"),

    /**
     * 系统异常
     */
    RC_500(500, "系统异常"),
    ;
    private final Integer code;
    private final String message;
}
