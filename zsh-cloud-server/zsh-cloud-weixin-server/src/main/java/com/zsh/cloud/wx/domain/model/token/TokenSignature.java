package com.zsh.cloud.wx.domain.model.token;

import com.zsh.cloud.common.core.domain.Entity;
import com.zsh.cloud.wx.domain.model.account.Token;

/**
 * @author hang
 * @version 1.0
 * @date 2022/03/16 14:26
 */
public class TokenSignature implements Entity<TokenSignature> {
    
    private Token token;
    
    private String timestamp;
    
    private String nonce;
    
    public TokenSignature(Token token, String timestamp, String nonce) {
        this.token = token;
        this.timestamp = timestamp;
        this.nonce = nonce;
    }
    
    @Override
    public boolean sameIdentityAs(TokenSignature other) {
        return false;
    }
    
    public Token getToken() {
        return token;
    }
    
    public String getTimestamp() {
        return timestamp;
    }
    
    public String getNonce() {
        return nonce;
    }
}
