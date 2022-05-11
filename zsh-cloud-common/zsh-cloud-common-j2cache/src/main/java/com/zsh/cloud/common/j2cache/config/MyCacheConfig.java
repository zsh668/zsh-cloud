package com.zsh.cloud.common.j2cache.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;

/**
 * 覆盖 SpringCache 相关配置.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/24 13:53
 */
public class MyCacheConfig extends CachingConfigurerSupport {
    
    /**
     * 解决注解：Cacheable 没有指定key时，会将key生成为 ${value}:SimpleKey [].
     * </p>
     * eg： @Cacheable(value = "user") ->  user:SimpleKey []
     *
     * @return
     */
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, objects) -> "";
    }
}
