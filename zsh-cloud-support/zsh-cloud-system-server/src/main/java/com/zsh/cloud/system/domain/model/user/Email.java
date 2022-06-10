package com.zsh.cloud.system.domain.model.user;

import com.zsh.cloud.common.core.domain.ValueObject;
import com.zsh.cloud.common.core.exception.code.enums.GlobalErrorCode;
import com.zsh.cloud.common.core.util.ServiceAssert;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * 邮箱.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 11:53
 */
public final class Email implements ValueObject<Email> {
    
    private final String email;
    
    /**
     * 有效性正则
     */
    private static final Pattern VALID_PATTERN = Pattern.compile(
            "^([a-z0-9A-Z]+[-|.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
    
    public Email(final String email) {
        if (StringUtils.isNotBlank(email)) {
            ServiceAssert.isTrue(VALID_PATTERN.matcher(email).matches(), GlobalErrorCode.BAD_REQUEST.getCode(),
                    "账号不能为空");
        }
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }
    
    @Override
    public boolean sameValueAs(Email other) {
        return other != null && this.email.equals(other.email);
    }
    
    @Override
    public String toString() {
        return email;
    }
}
