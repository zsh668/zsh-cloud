package com.zsh.cloud.wx.domain.service;

import com.zsh.cloud.common.weixin.util.aes.SHA1;
import com.zsh.cloud.wx.domain.model.token.Signature;
import com.zsh.cloud.wx.domain.model.token.TokenSignature;

/**
 * 校验signature
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/15 17:36
 */
public class WechatSignatureValidateService {
    
    public boolean validate(TokenSignature account, Signature signature) {
        return signature.getSign()
                .equals(SHA1.getSHA1(account.getToken().getToken(), account.getTimestamp(), account.getNonce()));
    }
}
