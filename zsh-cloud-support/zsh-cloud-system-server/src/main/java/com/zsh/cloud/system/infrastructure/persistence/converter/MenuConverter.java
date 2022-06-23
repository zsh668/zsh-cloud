package com.zsh.cloud.system.infrastructure.persistence.converter;

import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.enums.BooleanEnum;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.Assert;
import com.zsh.cloud.system.domain.model.menu.Menu;
import com.zsh.cloud.system.domain.model.menu.MenuId;
import com.zsh.cloud.system.domain.model.menu.MenuName;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysMenuDO;

/**
 * 菜单Converter.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 17:00
 */
public class MenuConverter {
    
    /**
     * 转换.
     *
     * @param sysMenuDO
     * @return
     */
    public static Menu toMenu(SysMenuDO sysMenuDO) {
        if (sysMenuDO == null) {
            return null;
        }
        return new Menu(new MenuId(sysMenuDO.getId()), new MenuName(sysMenuDO.getMenuName()),
                new MenuId(sysMenuDO.getParentId()), sysMenuDO.getPath(), sysMenuDO.getComponent(), sysMenuDO.getIcon(),
                sysMenuDO.getSortValue(), IDict.getByCode(BooleanEnum.class, sysMenuDO.getIsPublic()),
                IDict.getByCode(StatusEnum.class, sysMenuDO.getStatus()), sysMenuDO.getDescribe());
    }
    
    /**
     * 转换.
     *
     * @param menu
     * @return
     */
    public static SysMenuDO fromMenu(Menu menu) {
        Assert.notNull(menu, ServiceErrorCode.MENU_NOT_EXISTS);
        SysMenuDO sysMenuDO = new SysMenuDO();
        sysMenuDO.setId(menu.getMenuId() == null ? null : menu.getMenuId().getId());
        sysMenuDO.setMenuName(menu.getMenuName() == null ? null : menu.getMenuName().getName());
        sysMenuDO.setParentId(menu.getParentId() == null ? null : menu.getParentId().getId());
        sysMenuDO.setIsPublic(menu.getIsPublic() == null ? null : menu.getIsPublic().getCode());
        sysMenuDO.setPath(menu.getPath());
        sysMenuDO.setComponent(menu.getComponent());
        sysMenuDO.setSortValue(menu.getSortValue());
        sysMenuDO.setIcon(menu.getIcon());
        sysMenuDO.setStatus(menu.getStatus() == null ? null : menu.getStatus().getCode());
        sysMenuDO.setDescribe(menu.getDescribe());
        return sysMenuDO;
    }
}
