package com.zsh.cloud.common.tenant.config;

import com.zsh.cloud.common.tenant.interceptor.TenantHandlerInterceptor;
import com.zsh.cloud.common.tenant.properties.TenantProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/3/24 17:11
 */
@Configuration
public class TenantWebConfigurer implements WebMvcConfigurer {
    
    @Autowired
    private TenantProperties tenantProperties;
    
    @Autowired
    private TenantHandlerInterceptor tenantHandlerInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] commonPathPatterns = tenantProperties.getIgnoreUrls().toArray(new String[0]);
        registry.addInterceptor(tenantHandlerInterceptor).excludePathPatterns(commonPathPatterns);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
