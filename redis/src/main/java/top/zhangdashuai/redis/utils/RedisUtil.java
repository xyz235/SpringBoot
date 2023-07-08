package top.zhangdashuai.redis.utils;

import lombok.Getter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zhangdashuai
 */
@Component
public class RedisUtil {

    @Getter
    private static RedisTemplate<String, String> redisTemplate;

    public RedisUtil(RedisTemplate<String, String> redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }
}
