package com.zsh.cloud.system.domain.model.menu;

import com.zsh.cloud.common.core.domain.ValueObject;
import org.apache.commons.lang3.StringUtils;

/**
 * 菜单ID.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 11:53
 */
public class MenuId implements ValueObject<MenuId> {
    
    /**
     * 菜单ID.
     */
    private final String id;
    
    public MenuId(final String id) {
        if (StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("菜单id不能为空");
        }
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    
    @Override
    public boolean sameValueAs(MenuId other) {
        return other != null && this.id.equals(other.id);
    }
    
    @Override
    public String toString() {
        return id;
    }
}
