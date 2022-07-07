package com.zsh.cloud.auth.sevice;

/**
 * 登录服务.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/7 11:01
 */
public interface LoginService {
    
    /**
     * 重置错误次数.
     *
     * @param username
     */
    void restErrorNum(String username);
    
    /**
     * 更新错误次数.
     *
     * @param username
     */
    void updateErrorNum(String username);
    
    /**
     * 获取锁 对象.
     *
     * @param username
     * @return
     */
    Object getLock(String username);
}
