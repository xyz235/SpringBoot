package top.zhangdashuai.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhangdashuai
 */
@Data
@AllArgsConstructor
public class Stock {

    /**
     * 股票交易代码
     */
    private Integer code;

    /**
     * 现价
     */
    private Integer price;
}
