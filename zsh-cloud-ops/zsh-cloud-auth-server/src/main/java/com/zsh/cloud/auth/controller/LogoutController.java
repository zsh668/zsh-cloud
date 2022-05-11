package com.zsh.cloud.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注销.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/11 18:03
 */
@Api(tags = "注销")
@RestController
@RequestMapping("/oauth")
@Slf4j
public class LogoutController {
    
    /**
     * 退出.
     *
     * @return
     */
    @ApiOperation(value = "注销", notes = "logout")
    @PostMapping("/logout")
    public Boolean logout() {
        
        return Boolean.TRUE;
    }
}
