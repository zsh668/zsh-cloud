package com.zsh.cloud.auth.controller;

import com.zsh.cloud.auth.sevice.LoginService;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

/**
 * 认证中心.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/11 17:47
 */
@Api(tags = "认证中心")
@RestController
@RequestMapping("/oauth")
@Slf4j
public class AuthController {
    
    @Autowired
    private TokenEndpoint tokenEndpoint;
    
    @Autowired
    private LoginService loginService;
    
    /**
     * 登录.
     *
     * @param principal
     * @param parameters
     * @return
     * @throws HttpRequestMethodNotSupportedException
     */
    @PostMapping("/token")
    @ApiOperation(value = "OAuth2认证", notes = "login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grant_type", defaultValue = "password", value = "授权模式", required = true),
            @ApiImplicitParam(name = "client_id", defaultValue = "client", value = "Oauth2客户端ID", required = true),
            @ApiImplicitParam(name = "client_secret", defaultValue = "123456", value = "Oauth2客户端秘钥", required = true),
            @ApiImplicitParam(name = "refresh_token", value = "刷新token"),
            @ApiImplicitParam(name = "username", defaultValue = "admin", value = "登录用户名"),
            @ApiImplicitParam(name = "password", defaultValue = "123456", value = "登录密码")})
    public OAuth2AccessToken postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) {
        try {
            OAuth2AccessToken accessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
            // 重置 错误次数
            loginService.restErrorNum(parameters.get("username"));
            return accessToken;
        } catch (Exception e) {
            // 更新 错误次数
            loginService.updateErrorNum(parameters.get("username"));
            Object obj = loginService.getLock(parameters.get("username"));
            Assert.isTrue(obj == null, ServiceErrorCode.USER_ACCOUNT_PASSWORD_LOCK_ERROR);
            Assert.notTrue(obj == null, ServiceErrorCode.USER_ACCOUNT_PASSWORD_ERROR);
        }
        return null;
    }
    
}
