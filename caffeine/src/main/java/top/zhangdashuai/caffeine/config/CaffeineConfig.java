package top.zhangdashuai.caffeine.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangdashuai
 */
@Configuration
public class CaffeineConfig {

    @Bean
    public Cache<String, String> cache() {
        return Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)
                .build();
    }
}
