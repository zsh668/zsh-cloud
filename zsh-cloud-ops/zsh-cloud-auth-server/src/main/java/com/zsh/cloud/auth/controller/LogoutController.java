package com.zsh.cloud.auth.controller;

import cn.hutool.json.JSONObject;
import com.zsh.cloud.common.core.constant.AuthConstants;
import com.zsh.cloud.common.core.util.RequestUtils;
import com.zsh.cloud.common.redis.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private RedisService redisService;
    
    /**
     * 退出.
     *
     * @return
     */
    @ApiOperation(value = "注销", notes = "logout")
    @PostMapping("/logout")
    public Boolean logout() {
        JSONObject jsonObject = RequestUtils.getJwtPayload();
        String jti = jsonObject.getStr(AuthConstants.JWT_JTI);
        long exp = jsonObject.getLong(AuthConstants.JWT_EXP);
        
        long currentTimeSeconds = System.currentTimeMillis() / 1000;
        
        if (exp < currentTimeSeconds) {
            return Boolean.TRUE;
        }
        redisService.set(AuthConstants.TOKEN_BLACKLIST_PREFIX + jti, null, (exp - currentTimeSeconds));
        return Boolean.TRUE;
    }
}
