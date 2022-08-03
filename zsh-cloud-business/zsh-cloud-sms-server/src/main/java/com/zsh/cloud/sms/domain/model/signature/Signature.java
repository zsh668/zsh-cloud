package com.zsh.cloud.sms.domain.model.signature;

import com.zsh.cloud.common.core.domain.Entity;
import lombok.Getter;

/**
 * 签名.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/8/1 16:54
 */
@Getter
public class Signature implements Entity<Signature> {
    
    /**
     * 签名id.
     */
    private SignatureId signatureId;
    
    /**
     * 签名名称.
     */
    private SignatureName signatureName;
    
    /**
     * 签名编码.
     */
    private SignatureCode signatureCode;
    
    /**
     * 签名内容.
     */
    private String content;
    
    /**
     * 备注.
     */
    private String describe;
    
    @Override
    public boolean sameIdentityAs(Signature other) {
        return other != null && signatureId.sameValueAs(other.signatureId);
    }
}
