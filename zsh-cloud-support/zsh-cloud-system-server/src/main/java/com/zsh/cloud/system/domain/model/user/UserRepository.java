package com.zsh.cloud.system.domain.model.user;

import java.util.List;

/**
 * 用户-Repository接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/24 18:30
 */
public interface UserRepository {
    
    /**
     * 通过账号获取用户.
     *
     * @param account
     * @return
     */
    List<User> find(Account account);
    
    /**
     * 通过用户ID获取用户
     *
     * @param userId
     * @return
     */
    User find(UserId userId);
    
    /**
     * 保存.
     *
     * @param user
     * @return
     */
    UserId store(User user);
    
    /**
     * 通过superior获取用户.
     *
     * @param superior
     * @return
     */
    List<User> findBySuperior(UserId superior);
    
    /**
     * 删除.
     *
     * @param userIds
     */
    void remove(List<UserId> userIds);
    
    /**
     * 重置密码.
     *
     * @param ids
     */
    void reset(List<String> ids);
}
