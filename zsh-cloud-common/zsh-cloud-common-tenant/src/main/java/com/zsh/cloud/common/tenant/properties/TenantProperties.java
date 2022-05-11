package com.zsh.cloud.common.tenant.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

/**
 * 多租户配置.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/3/24 14:11
 */
@ConfigurationProperties(prefix = "zsh.cloud.tenant")
@Data
public class TenantProperties {
    
    /**
     * 租户是否开启.
     */
    private static final Boolean ENABLE_DEFAULT = true;
    
    /**
     * 是否开启.
     */
    private Boolean enable = ENABLE_DEFAULT;
    
    /**
     * 需要忽略多租户的请求.
     */
    private Set<String> ignoreUrls;
    
    /**
     * 需要忽略多租户的表.
     */
    private Set<String> ignoreTables;
}
