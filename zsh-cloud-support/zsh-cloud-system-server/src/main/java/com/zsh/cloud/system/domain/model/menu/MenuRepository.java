package com.zsh.cloud.system.domain.model.menu;

import java.util.List;

/**
 * 菜单-Repository接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 14:17
 */
public interface MenuRepository {
    
    /**
     * 获取菜单.
     *
     * @param menuId
     * @return
     */
    Menu find(MenuId menuId);
    
    /**
     * 获取菜单.
     *
     * @param menuName
     * @return
     */
    List<Menu> find(MenuName menuName);
    
    /**
     * 获取菜单.
     *
     * @param parentId
     * @return
     */
    List<Menu> queryList(MenuId parentId);
    
    /**
     * 保存.
     *
     * @param menu
     * @return
     */
    MenuId store(Menu menu);
    
    /**
     * 批量删除.
     *
     * @param menuIds
     */
    void remove(List<MenuId> menuIds);
}
