package com.zsh.cloud.system.domain.model.user;

import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.domain.ValueObject;

/**
 * 性别 枚举.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/19 18:00
 */
public enum GenderEnum implements ValueObject<GenderEnum>, IDict<Integer> {
    
    /**
     * M="男".
     */
    M(1, "男"),
    
    /**
     * W="女".
     */
    W(2, "女"),
    
    /**
     * N="未知".
     */
    N(3, "未知");
    
    GenderEnum(Integer code, String text) {
        init(code, text);
    }
    
    @Override
    public boolean sameValueAs(final GenderEnum other) {
        return this.equals(other);
    }
}
