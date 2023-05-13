package top.zhangdashuai.redis.utils;

import com.google.common.collect.Lists;
import lombok.Getter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangdashuai
 */
@Component
public class RedisLockUtil {

    @Getter
    private static RedisTemplate<String, String> redisTemplate;

    public RedisLockUtil(RedisTemplate<String, String> redisTemplate) {
        RedisLockUtil.redisTemplate = redisTemplate;
    }

    public static boolean tryLock(String lockKey, String lockValue, int expireTimeSeconds) {
        return Optional.ofNullable(redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, expireTimeSeconds, TimeUnit.SECONDS)).orElse(Boolean.FALSE);
    }

    public static boolean tryUnlock(String lockKey, String lockValue) {
        return Optional.ofNullable(redisTemplate.execute(RedisScript.of("if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end", Boolean.class), Lists.newArrayList(lockKey), lockValue)).orElse(Boolean.FALSE);
    }
}
