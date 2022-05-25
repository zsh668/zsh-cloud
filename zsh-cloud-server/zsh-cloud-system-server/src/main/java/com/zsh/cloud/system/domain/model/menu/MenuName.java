package com.zsh.cloud.system.domain.model.menu;

import com.zsh.cloud.common.core.domain.ValueObject;
import com.zsh.cloud.common.core.exception.code.enums.GlobalErrorCode;
import com.zsh.cloud.common.core.util.ServiceAssert;
import org.apache.commons.lang3.StringUtils;

/**
 * 菜单名称.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 11:53
 */
public class MenuName implements ValueObject<MenuName> {
    
    /**
     * 菜单名称.
     */
    private final String name;
    
    public MenuName(final String name) {
        ServiceAssert.isTrue(StringUtils.isNotBlank(name), GlobalErrorCode.BAD_REQUEST.getCode(), "菜单名称不能为空");
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public boolean sameValueAs(MenuName other) {
        return other != null && this.name.equals(other.name);
    }
    
    @Override
    public String toString() {
        return name;
    }
}
