package com.zsh.cloud.system.domain.model.menu;

import com.zsh.cloud.common.core.domain.Entity;
import com.zsh.cloud.common.core.enums.StatusEnum;
import lombok.Getter;

/**
 * 菜单.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 16:44
 */
@Getter
public class Menu implements Entity<Menu> {
    
    /**
     * 菜单ID.
     */
    private MenuId menuId;
    
    /**
     * 菜单名称.
     */
    private MenuName menuName;
    
    /**
     * 状态.
     */
    private StatusEnum status;
    
    /**
     * 是否有效.
     *
     * @return
     */
    public boolean isEnable() {
        return status == StatusEnum.ENABLE;
    }
    
    /**
     * 启用、禁用.
     */
    public void disable() {
        this.status = isEnable() ? StatusEnum.DISABLE : StatusEnum.ENABLE;
    }
    
    @Override
    public boolean sameIdentityAs(Menu other) {
        return other != null && menuId.sameValueAs(other.menuId);
    }
}
