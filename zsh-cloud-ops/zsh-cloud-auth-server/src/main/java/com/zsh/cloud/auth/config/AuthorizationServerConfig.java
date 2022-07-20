package com.zsh.cloud.auth.config;

import com.zsh.cloud.auth.domain.User;
import com.zsh.cloud.auth.sevice.impl.RedisClientDetailsServiceImpl;
import com.zsh.cloud.auth.sevice.impl.UserDetailsServiceImpl;
import com.zsh.cloud.common.core.constant.AuthConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 授权服务配置.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/13 11:04
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    
    /**
     * 7天.
     */
    private static final long EXPIRATION = 86400 * 7 * 1000L;
    
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
    }
    
    /**
     * 配置客户端详情(数据库)
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        RedisClientDetailsServiceImpl redisClientDetailsService = new RedisClientDetailsServiceImpl(dataSource);
        redisClientDetailsService.setPasswordEncoder(passwordEncoder);
        redisClientDetailsService.setFindClientDetailsSql(AuthConstants.FIND_CLIENT_DETAILS_SQL);
        redisClientDetailsService.setSelectClientDetailsSql(AuthConstants.SELECT_CLIENT_DETAILS_SQL);
        clients.withClientDetails(redisClientDetailsService);
    }
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> tokenEnhancers = new ArrayList<>();
        tokenEnhancers.add(tokenEnhancer());
        tokenEnhancers.add(jwtAccessTokenConverter());
        tokenEnhancerChain.setTokenEnhancers(tokenEnhancers);
        
        endpoints
                // 指定token存储位置
                //.tokenStore(tokenStore())
                // 自定义生成令牌
                .tokenEnhancer(tokenEnhancerChain)
                // 指定认证管理器
                .authenticationManager(authenticationManager)
                // 非对称加密算法
                .accessTokenConverter(jwtAccessTokenConverter())
                // 用户账号密码认证
                .userDetailsService(userDetailsService)
                // refresh token有两种使用方式：重复使用(true)、非重复使用(false)，默认为true
                //  1 重复使用：access token过期刷新时， refresh token过期时间未改变，仍以初次生成的时间为准
                //  2 非重复使用：access token过期刷新时， refresh token过期时间延续，在refresh token有效期内刷新便永不失效达到无需再次登录的目的
                .reuseRefreshTokens(true);
    }
    
    /**
     * 使用非对称加密算法对token签名
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyPair());
        return converter;
    }
    
    /**
     * 从classpath下的密钥库中获取密钥对(公钥+私钥)
     */
    @Bean
    public KeyPair keyPair() {
        KeyStoreKeyFactory factory = new KeyStoreKeyFactory(new ClassPathResource("zsh-cloud.jks"),
                "QazWsx123".toCharArray());
        return factory.getKeyPair("zsh-cloud", "QazWsx123".toCharArray());
    }
    
    
    /**
     * JWT内容增强
     *
     * @return
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            User user = (User) authentication.getUserAuthentication().getPrincipal();
            Map<String, Object> map = new HashMap<>(8);
            map.put(AuthConstants.USER_ID_KEY, user.getId());
            map.put(AuthConstants.USER_NAME_KEY, user.getUsername());
            map.put(AuthConstants.TENANT_ID_KEY, user.getTenantId());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);
            // 过期时间 7天
            ((DefaultOAuth2AccessToken) accessToken).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION));
            return accessToken;
        };
    }
    
}
