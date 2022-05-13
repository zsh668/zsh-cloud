package com.zsh.cloud.common.core.enums;

import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.domain.ValueObject;

/**
 * 状态枚举.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/29 10:18
 */
public enum StatusEnum implements ValueObject<StatusEnum>, IDict<Integer> {
    
    /**
     * 有效.
     */
    ENABLE(1, "有效"),
    
    /**
     * 禁用.
     */
    DISABLE(0, "禁用");
    
    StatusEnum(Integer code, String text) {
        init(code, text);
    }
    
    @Override
    public boolean sameValueAs(final StatusEnum other) {
        return this.equals(other);
    }
}
