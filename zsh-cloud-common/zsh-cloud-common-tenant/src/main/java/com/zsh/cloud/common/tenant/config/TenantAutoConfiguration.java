package com.zsh.cloud.common.tenant.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.zsh.cloud.common.mybatis.util.MyBatisUtils;
import com.zsh.cloud.common.tenant.handler.TenantDatabaseHandler;
import com.zsh.cloud.common.tenant.interceptor.TenantHandlerInterceptor;
import com.zsh.cloud.common.tenant.properties.TenantProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 多租户自动配置.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/3/24 14:15
 */
@Configuration
@ConditionalOnProperty(prefix = "zsh.cloud.tenant", value = "enable", matchIfMissing = true)
@EnableConfigurationProperties(TenantProperties.class)
public class TenantAutoConfiguration {
    
    @Bean
    public TenantHandlerInterceptor tenantHandlerInterceptor() {
        return new TenantHandlerInterceptor();
    }
    
    /**
     * 租户插件.
     *
     * @param properties
     * @param interceptor
     * @return
     */
    @Bean
    public TenantLineInnerInterceptor tenantLineInnerInterceptor(TenantProperties properties,
            MybatisPlusInterceptor interceptor) {
        TenantLineInnerInterceptor inner = new TenantLineInnerInterceptor(new TenantDatabaseHandler(properties));
        // 添加到 interceptor 中
        // 需要加在首个，主要是为了在分页插件前面。这个是 MyBatis Plus 的规定
        MyBatisUtils.addInterceptor(interceptor, inner, 0);
        return inner;
    }
}
