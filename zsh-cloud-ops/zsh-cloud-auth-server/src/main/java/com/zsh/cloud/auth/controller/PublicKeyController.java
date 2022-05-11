package com.zsh.cloud.auth.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
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
    @GetMapping("/getPublicKey")
    public Map<String, Object> loadPublicKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }
    
}
