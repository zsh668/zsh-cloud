package com.zsh.cloud.common.mybatis.datascope.enums;

import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.domain.ValueObject;

/**
 * 数据权限范围枚举.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/12 11:21
 */
public enum DataScopeTypeEnum implements ValueObject<DataScopeTypeEnum>, IDict<Integer> {
    
    ALL(1, "全部"),
    THIS_LEVEL(2, "本级"),
    THIS_LEVEL_CHILDREN(3, "本级以及子级"),
    CUSTOMIZE(4, "自定义"),
    SELF(5, "个人");
    
    DataScopeTypeEnum(Integer code, String text) {
        init(code, text);
    }
    
    @Override
    public boolean sameValueAs(DataScopeTypeEnum other) {
        return this.equals(other);
    }
}
