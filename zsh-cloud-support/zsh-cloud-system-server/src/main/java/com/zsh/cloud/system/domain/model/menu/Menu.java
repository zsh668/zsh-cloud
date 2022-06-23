package com.zsh.cloud.system.domain.model.menu;

import com.zsh.cloud.common.core.domain.Entity;
import com.zsh.cloud.common.core.enums.BooleanEnum;
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
     * 父ID.
     */
    public static final String PARENT_ID = "0";
    
    /**
     * 菜单ID.
     */
    private MenuId menuId;
    
    /**
     * 菜单名称.
     */
    private MenuName menuName;
    
    /**
     * 菜单父ID.
     */
    private MenuId parentId;
    
    /**
     * 对应路由path.
     */
    private String path;
    
    /**
     * 对应路由组件component.
     */
    private String component;
    
    /**
     * 菜单图标.
     */
    private String icon;
    
    /**
     * 排序.
     */
    private Integer sortValue;
    
    /**
     * 是否公开菜单 1是，0否.
     */
    private BooleanEnum isPublic;
    
    /**
     * 状态.
     */
    private StatusEnum status;
    
    /**
     * 功能描述.
     */
    private String describe;
    
    public Menu(MenuName menuName, MenuId parentId, String path, String component, String icon, Integer sortValue,
            BooleanEnum isPublic, String describe) {
        this.menuName = menuName;
        this.parentId = parentId;
        this.path = path;
        this.component = component;
        this.icon = icon;
        this.sortValue = sortValue;
        this.isPublic = isPublic;
        this.status = StatusEnum.ENABLE;
        this.describe = describe;
    }
    
    public Menu(MenuId menuId, MenuName menuName, MenuId parentId, String path, String component, String icon,
            Integer sortValue, BooleanEnum isPublic, StatusEnum status, String describe) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.parentId = parentId;
        this.path = path;
        this.component = component;
        this.icon = icon;
        this.sortValue = sortValue;
        this.isPublic = isPublic;
        this.status = status;
        this.describe = describe;
    }
    
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
