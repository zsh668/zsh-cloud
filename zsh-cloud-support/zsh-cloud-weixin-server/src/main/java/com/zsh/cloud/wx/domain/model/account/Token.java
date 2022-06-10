package com.zsh.cloud.wx.domain.model.account;

import com.zsh.cloud.common.core.domain.ValueObject;
import org.springframework.util.ObjectUtils;

/**
 * token
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/16 10:57
 */
public class Token implements ValueObject<Token> {
    
    private String token;
    
    public Token(final String token) {
        if (ObjectUtils.isEmpty(token)) {
            throw new IllegalArgumentException("token不能为空");
        }
        this.token = token;
    }
    
    public String getToken() {
        return token;
    }
    
    @Override
    public boolean sameValueAs(Token other) {
        return other != null && this.token.equals(other.token);
    }
    
    @Override
    public String toString() {
        return token;
    }
}
