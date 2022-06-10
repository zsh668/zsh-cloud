package com.zsh.cloud.wx.domain.specification;

import com.zsh.cloud.common.core.domain.specification.AbstractSpecification;
import com.zsh.cloud.wx.domain.model.account.Account;

/**
 * 签名Specification
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/16 11:28
 */
public class SignatureSpecification extends AbstractSpecification<Account> {
    
    @Override
    public boolean isSatisfiedBy(Account account) {
        
        return false;
    }
}
