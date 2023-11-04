package top.zhangdashuai.service.impl;

import org.springframework.stereotype.Service;
import top.zhangdashuai.pojo.Stock;
import top.zhangdashuai.service.Strategy;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangdashuai
 */
@Service("highPriceRank")
public class HighPriceRank implements Strategy {

    @Override
    public List<Stock> sort(List<Stock> source) {
        return source.stream()
                .sorted(Comparator.comparing(Stock::getPrice).reversed())
                .collect(Collectors.toList());
    }
}
