package com.zsh.cloud.system.domain.model.user;

import com.zsh.cloud.common.core.domain.ValueObject;
import org.apache.commons.lang3.StringUtils;

/**
 * 账号.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 10:23
 */
public class Account implements ValueObject<Account> {
    
    private final String account;
    
    public Account(final String account) {
        if (StringUtils.isEmpty(account)) {
            throw new IllegalArgumentException("账号不能为空");
        }
        this.account = account;
    }
    
    public String getAccount() {
        return account;
    }
    
    @Override
    public boolean sameValueAs(Account other) {
        return other != null && this.account.equals(other.account);
    }
    
    @Override
    public String toString() {
        return account;
    }
}
