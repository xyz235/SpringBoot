package top.zhangdashuai.service;

import top.zhangdashuai.pojo.Stock;

import java.util.List;

/**
 * @author zhangdashuai
 */
public interface Strategy {

    /**
     * 将股票列表排序
     *
     * @param source 源数据
     * @return 排序后的榜单
     */
    List<Stock> sort(List<Stock> source);
}
