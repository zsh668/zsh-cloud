package com.zsh.cloud.wx.domain.model.account;

import com.zsh.cloud.common.core.domain.Entity;

/**
 * 微信公众号账号
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/16 10:18
 */
public class Account implements Entity<Account> {
    
    private Appid appid;
    
    private Token token;
    
    private String aesKey;
    
    public Account(Appid appid, Token token, String aesKey) {
        this.appid = appid;
        this.token = token;
        this.aesKey = aesKey;
    }
    
    @Override
    public boolean sameIdentityAs(Account other) {
        return other != null && appid.sameValueAs(other.appid);
    }
    
    public Appid getAppid() {
        return appid;
    }
    
    public Token getToken() {
        return token;
    }
    
    public String getAesKey() {
        return aesKey;
    }
}
