package com.zsh.cloud.system.domain.model.user;

import com.zsh.cloud.common.core.domain.ValueObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.regex.Pattern;

/**
 * 手机.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 11:53
 */
public final class Mobile implements ValueObject<Mobile> {
    
    private final String mobile;
    
    /**
     * 有效性正则.
     */
    private static final Pattern VALID_PATTERN = Pattern.compile(
            "^((13[0-9])|(14[0-1,4-9])|(15[0-3,5-9])|(16[2,5-7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$");
    
    public Mobile(final String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            throw new IllegalArgumentException("手机号不能为空");
        }
        Validate.isTrue(VALID_PATTERN.matcher(mobile).matches(), "手机号格式不正确");
        this.mobile = mobile;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    @Override
    public boolean sameValueAs(Mobile other) {
        return other != null && this.mobile.equals(other.mobile);
    }
    
    @Override
    public String toString() {
        return mobile;
    }
}
