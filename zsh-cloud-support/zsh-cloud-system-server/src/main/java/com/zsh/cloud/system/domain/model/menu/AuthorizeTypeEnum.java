package com.zsh.cloud.system.domain.model.menu;

import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.domain.ValueObject;

/**
 * 权限类型 枚举.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/30 10:39
 */
public enum AuthorizeTypeEnum implements ValueObject<AuthorizeTypeEnum>, IDict<Integer> {
    
    /**
     * MENU="菜单".
     */
    MENU(1, "菜单"),
    
    /**
     * RESOURCE="资源".
     */
    RESOURCE(2, "资源");
    
    AuthorizeTypeEnum(Integer code, String text) {
        init(code, text);
    }
    
    @Override
    public boolean sameValueAs(AuthorizeTypeEnum other) {
        return this.equals(other);
    }
}
