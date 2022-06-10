package com.zsh.cloud.wx.domain.model.token;

import com.zsh.cloud.common.core.domain.ValueObject;
import org.springframework.util.ObjectUtils;

/**
 * 微信加密签名
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/16 10:24
 */
public class Signature implements ValueObject<Signature> {
    
    private String sign;
    
    public Signature(String sign) {
        if (ObjectUtils.isEmpty(sign)) {
            throw new IllegalArgumentException("微信加密签名不能为空");
        }
        this.sign = sign;
    }
    
    public String getSign() {
        return sign;
    }
    
    @Override
    public boolean sameValueAs(Signature other) {
        return other != null && this.sign.equals(other.sign);
    }
    
    @Override
    public String toString() {
        return sign;
    }
}
