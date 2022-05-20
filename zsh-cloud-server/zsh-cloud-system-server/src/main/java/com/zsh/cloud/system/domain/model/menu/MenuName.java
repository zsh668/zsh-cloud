package com.zsh.cloud.system.domain.model.menu;

import com.zsh.cloud.common.core.domain.ValueObject;
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
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("菜单名称不能为空");
        }
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
