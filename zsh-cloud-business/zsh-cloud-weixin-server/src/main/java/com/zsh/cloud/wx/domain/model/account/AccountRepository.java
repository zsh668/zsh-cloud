package com.zsh.cloud.wx.domain.model.account;

/**
 * @author hang
 * @version 1.0
 * @date 2022/03/15 18:28
 */
public interface AccountRepository {
    
    Account find(AppId appid);
}
