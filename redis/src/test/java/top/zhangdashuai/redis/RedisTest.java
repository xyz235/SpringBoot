package top.zhangdashuai.redis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.zhangdashuai.redis.utils.RedisUtil;

@SpringBootTest
public class RedisTest {

    @Test
    public void redisTest() {
        System.out.println(RedisUtil.getRedisTemplate().opsForValue().increment("main"));
    }
}
