package com.zsh.cloud.common.j2cache.config;

import net.oschina.j2cache.cache.support.J2CacheCacheManger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 覆盖 SpringCache 相关配置.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/24 13:53
 */
@EnableCaching
public class MyCacheConfig extends CachingConfigurerSupport {
    
    @Value("${spring.application.name:unknown}")
    private String appName;
    
    @Autowired
    private J2CacheCacheManger j2CacheCacheManger;
    
    @Override
    public CacheManager cacheManager() {
        return j2CacheCacheManger;
    }
    
    /**
     * 解决注解：Cacheable 没有指定key时，会将key生成为 ${value}:SimpleKey [].
     * </p>
     * eg： @Cacheable(value = "user") ->  user:SimpleKey []
     *
     * @return
     */
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> Stream.of(params).map(String::valueOf).collect(Collectors.joining(","));
    }
}
