package com.zsh.cloud.common.core.constant;

import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;

/**
 * 用于统一管理和生成缓存的region和key.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/19 18:59
 */
public interface CacheKey {
    
    /**
     * 验证码 前缀 完整key: captcha:{key} -> str
     */
    String CAPTCHA = "captcha";
    
    /**
     * OAuth缓存前缀
     */
    String OAUTH_ACCESS = "oauth:access:";
    
    /**
     * OAuth客户端信息
     */
    String CLIENT_DETAILS_KEY = "oauth:client:details";
    
    /**
     * 黑名单token前缀
     */
    String TOKEN_BLACKLIST_PREFIX = "auth:token:blacklist";
    
    /**
     * 重复请求 前缀
     */
    String REPEAT_REQUEST_REDIS_KEY = "repeat_request_redis_key:";
    
    /**
     * 登录用户 前缀
     */
    String LOGIN_USER_NAME_KEY = "login_user_name_key";
    
    /**
     * 登录用户锁 前缀
     */
    String LOGIN_USER_NAME_LOCK_KEY = "login_user_name_lock_key";
    
    /**
     * 构建key
     */
    static String buildKey(Object... args) {
        if (args.length == 1) {
            return String.valueOf(args[0]);
        } else if (args.length > 0) {
            return StrUtil.join(StrPool.COLON, args);
        } else {
            return "";
        }
    }
}
