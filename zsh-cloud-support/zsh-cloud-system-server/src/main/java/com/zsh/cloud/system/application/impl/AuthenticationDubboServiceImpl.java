package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.common.core.constant.CacheKey;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.Assert;
import com.zsh.cloud.system.api.dto.AuthenticationDTO;
import com.zsh.cloud.system.api.dubbo.AuthenticationDubboService;
import com.zsh.cloud.system.application.ResourceQueryService;
import com.zsh.cloud.system.application.assembler.AuthenticationDtoAssembler;
import com.zsh.cloud.system.domain.model.user.Account;
import com.zsh.cloud.system.domain.model.user.User;
import com.zsh.cloud.system.domain.model.user.UserRepository;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 身份验证应用服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/19 14:43
 */
@DubboService
public class AuthenticationDubboServiceImpl implements AuthenticationDubboService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ResourceQueryService resourceQueryService;
    
    @Autowired
    private AuthenticationDtoAssembler authenticationDtoAssembler;
    
    @Autowired
    private CacheChannel cache;
    
    @Override
    public AuthenticationDTO loginByUserName(String userName) {
        // 错误次数
        Assert.notTrue(cache.get(CacheKey.LOGIN_USER_NAME_LOCK_KEY, userName).getValue() != null,
                ServiceErrorCode.USER_ACCOUNT_PASSWORD_LOCK_ERROR);
        List<User> users = userRepository.find(new Account(userName));
        // 空 抛异常
        Assert.notEmpty(users, ServiceErrorCode.USER_ACCOUNT_PASSWORD_ERROR);
        User user = users.get(0);
        // 用户禁用
        Assert.isTrue(user.isEnable(), ServiceErrorCode.USER_NOT_ENABLE);
        // 密码过期
        Assert.notTrue(user.checkPasswordExpireTime(), ServiceErrorCode.USER_PASSWORD_EXPIRATION);
        AuthenticationDTO authentication = authenticationDtoAssembler.fromUser(user);
        authentication.setPermissionCodes(resourceQueryService.getPermissionCodes(user.getUserId().getId()));
        return authentication;
    }
}
