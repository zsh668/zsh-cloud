package com.zsh.cloud.auth.sevice.impl;

import com.zsh.cloud.auth.sevice.LoginService;
import com.zsh.cloud.common.core.constant.CacheKey;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/7 11:02
 */
@Service
public class LoginServiceImpl implements LoginService {
    
    private static final int LOCK_NUM = 5;
    
    @Autowired
    private CacheChannel cache;
    
    @Override
    public void restErrorNum(String username) {
        cache.evict(CacheKey.LOGIN_USER_NAME_KEY, username);
        cache.evict(CacheKey.LOGIN_USER_NAME_LOCK_KEY, username);
    }
    
    @Override
    public void updateErrorNum(String username) {
        CacheObject cacheObject = cache.get(CacheKey.LOGIN_USER_NAME_KEY, username);
        Object obj = cacheObject.getValue() == null ? 0 : cacheObject.getValue();
        int num = Integer.parseInt(String.valueOf(obj)) + 1;
        cache.set(CacheKey.LOGIN_USER_NAME_KEY, username, num);
        if (num == LOCK_NUM) {
            cache.set(CacheKey.LOGIN_USER_NAME_LOCK_KEY, username, username);
        }
    }
    
    @Override
    public Object getLock(String username) {
        CacheObject cacheObject = cache.get(CacheKey.LOGIN_USER_NAME_LOCK_KEY, username);
        return cacheObject.getValue();
    }
}
