package top.zhangdashuai.oss.config.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * @author zhangdashuai
 */
public class OssClientFactoryBean implements FactoryBean<OSS>
        , InitializingBean, DisposableBean {

    private OSS ossClient;

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    @Override
    public OSS getObject() {
        return this.ossClient;
    }

    @Override
    public Class<?> getObjectType() {
        return OSS.class;
    }

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(this.endpoint, "'endpoint' must be not null");
        Assert.notNull(this.accessKeyId, "'accessKeyId' must be not null");
        Assert.notNull(this.accessKeySecret, "'accessKeySecret' must be not null");
        this.ossClient = new OSSClientBuilder().build(this.endpoint, this.accessKeyId, this.accessKeySecret);
    }

    @Override
    public void destroy() {
        if (Objects.nonNull(ossClient)) {
            ossClient.shutdown();
        }
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }
}
