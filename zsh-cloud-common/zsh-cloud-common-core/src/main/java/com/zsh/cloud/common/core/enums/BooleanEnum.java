package com.zsh.cloud.common.core.enums;

import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.domain.ValueObject;

/**
 * 布尔枚举.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/29 10:18
 */
public enum BooleanEnum implements ValueObject<BooleanEnum>, IDict<Integer> {
    
    /**
     * 是.
     */
    TRUE(1, "是"),
    
    /**
     * 否.
     */
    FALSE(0, "否");
    
    BooleanEnum(Integer code, String text) {
        init(code, text);
    }
    
    @Override
    public boolean sameValueAs(final BooleanEnum other) {
        return this.equals(other);
    }
}
