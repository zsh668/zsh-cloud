package com.zsh.cloud.auth.sevice.impl;

import com.zsh.cloud.common.core.constant.CacheKey;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * 客户端信息配置.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/13 10:42
 */
public class RedisClientDetailsServiceImpl extends JdbcClientDetailsService {
    
    public RedisClientDetailsServiceImpl(DataSource dataSource) {
        super(dataSource);
    }
    
    @Cacheable(value = CacheKey.CLIENT_DETAILS_KEY, key = "#clientId", unless = "#result == null")
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        return super.loadClientByClientId(clientId);
    }
}
