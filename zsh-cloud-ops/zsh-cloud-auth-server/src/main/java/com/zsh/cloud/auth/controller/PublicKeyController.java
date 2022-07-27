package com.zsh.cloud.auth.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.zsh.cloud.auth.sevice.impl.RedisClientDetailsServiceImpl;
import com.zsh.cloud.common.web.handler.NotControllerResponseAdvice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 获取公钥接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/22 16:18
 */
@Api(tags = "公钥")
@RestController
@Slf4j
public class PublicKeyController {
    
    @Autowired
    private KeyPair keyPair;
    
    /**
     * 获取公钥.
     *
     * @return
     */
    @ApiOperation(value = "获取公钥", notes = "getPublicKey")
    @NotControllerResponseAdvice
    @GetMapping("/getPublicKey")
    public Map<String, Object> loadPublicKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }
    
    @Resource
    private JdbcClientDetailsService jdbcClientDetailsService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    /**
     * 初始化
     */
    // @GetMapping("/init")
    public void contextLoads() {
        //通过我们在鉴权服务中注入的JdbcClientDetailsService来插入一条Client信息
        BaseClientDetails baseClientDetails = new BaseClientDetails();
        baseClientDetails.setClientId("client");
        baseClientDetails.setScope(Collections.singletonList("all"));
        baseClientDetails.setClientSecret(passwordEncoder.encode("123456"));
        baseClientDetails.setAccessTokenValiditySeconds(3600);
        baseClientDetails.setRefreshTokenValiditySeconds(7200);
        List<String> grantTypes = new ArrayList<>();
        grantTypes.add("password");
        baseClientDetails.setAuthorizedGrantTypes(grantTypes);
        jdbcClientDetailsService.addClientDetails(baseClientDetails);
    }
}
