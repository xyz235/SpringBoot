package top.zhangdashuai.oss.config.oss;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cn-src
 */
@Configuration
public class OssAutoConfiguration {

    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    @Bean
    public OssClientFactoryBean ossClientFactoryBean() {
        OssClientFactoryBean factoryBean = new OssClientFactoryBean();
        factoryBean.setEndpoint(this.endpoint);
        factoryBean.setAccessKeyId(this.accessKeyId);
        factoryBean.setAccessKeySecret(this.accessKeySecret);
        return factoryBean;
    }
}

