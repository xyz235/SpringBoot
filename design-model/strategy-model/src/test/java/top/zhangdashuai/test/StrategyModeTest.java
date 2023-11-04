package top.zhangdashuai.test;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.zhangdashuai.pojo.Stock;
import top.zhangdashuai.service.Strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class StrategyModeTest {

    @Resource
    private Map<String, Strategy> stockMap;

    @Test
    public void strategyModeTest() {
        Strategy strategy = stockMap.get("highPriceRank");
        System.out.println(strategy.sort(createStockList()));
    }

    public List<Stock> createStockList() {
        List<Stock> stockList = new ArrayList<>();
        stockList.add(new Stock(1,1));
        stockList.add(new Stock(2,2));
        return stockList;
    }
}
