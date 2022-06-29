package com.zsh.cloud.auth.controller;

import com.zsh.cloud.auth.dto.query.LoginParamQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 登录.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/27 11:44
 */
@Api(tags = "登录")
@RestController
@RequestMapping("/oauth")
@Slf4j
public class LoginController {
    
    /**
     * 登录.
     *
     * @param loginParamQuery
     */
    @ApiOperation(value = "登录", notes = "登录")
    @PostMapping(value = "/login")
    public void login(@Valid @RequestBody LoginParamQuery loginParamQuery) {
    
    }
}
