package com.zsh.cloud.sms.domain.specification;

import com.zsh.cloud.common.core.domain.specification.AbstractSpecification;
import com.zsh.cloud.sms.domain.model.signature.Signature;
import com.zsh.cloud.sms.domain.model.signature.SignatureRepository;

/**
 * 用户创建Specification.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/24 14:48
 */
public class SignatureCreateSpecification extends AbstractSpecification<Signature> {
    
    private SignatureRepository signatureRepository;
    
    public SignatureCreateSpecification(SignatureRepository signatureRepository) {
        this.signatureRepository = signatureRepository;
    }
    
    @Override
    public boolean isSatisfiedBy(Signature signature) {
        // List<Signature> signatures = signatureRepository.find(signature.getAccount());
        // if (!CollectionUtils.isEmpty(signatures)) {
        //     signatures.forEach(u -> Assert.notTrue(u.getAccount().sameValueAs(user.getAccount()),
        //             ServiceErrorCode.USER_ACCOUNT_EXISTS));
        // }
        return true;
    }
}
