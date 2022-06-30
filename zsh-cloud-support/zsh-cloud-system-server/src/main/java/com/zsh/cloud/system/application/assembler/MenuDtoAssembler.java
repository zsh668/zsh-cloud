package com.zsh.cloud.system.application.assembler;

import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.enums.BooleanEnum;
import com.zsh.cloud.system.application.command.MenuCreateCommand;
import com.zsh.cloud.system.application.command.MenuUpdateCommand;
import com.zsh.cloud.system.application.dto.MenuDTO;
import com.zsh.cloud.system.application.dto.MenuTreeDTO;
import com.zsh.cloud.system.application.dto.VueRouterDTO;
import com.zsh.cloud.system.domain.model.menu.Menu;
import com.zsh.cloud.system.domain.model.menu.MenuId;
import com.zsh.cloud.system.domain.model.menu.MenuName;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysMenuDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 菜单Assembler.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 16:49
 */
@Mapper(componentModel = "spring")
public interface MenuDtoAssembler {
    
    MenuTreeDTO toDto(SysMenuDO menu);
    
    List<MenuTreeDTO> toDto(List<SysMenuDO> menuList);
    
    /**
     * 转换.
     *
     * @param menu
     * @return
     */
    default MenuDTO fromMenu(Menu menu) {
        if (menu == null) {
            return null;
        }
        MenuDTO menuDto = new MenuDTO();
        menuDto.setId(menu.getMenuId() == null ? null : menu.getMenuId().getId());
        menuDto.setMenuName(menu.getMenuName() == null ? null : menu.getMenuName().getName());
        menuDto.setParentId(menu.getParentId() == null ? null : menu.getParentId().getId());
        menuDto.setIsPublic(menu.getIsPublic() == null ? null : menu.getIsPublic().getCode());
        menuDto.setPath(menu.getPath());
        menuDto.setComponent(menu.getComponent());
        menuDto.setSortValue(menu.getSortValue());
        menuDto.setIcon(menu.getIcon());
        menuDto.setStatus(menu.getStatus() == null ? null : menu.getStatus().getCode());
        menuDto.setDescribe(menu.getDescribe());
        return menuDto;
    }
    
    /**
     * 转换.
     *
     * @param menuCommand
     * @return
     */
    default Menu toMenu(MenuCreateCommand menuCommand) {
        return new Menu(new MenuName(menuCommand.getMenuName()), new MenuId(menuCommand.getParentId()),
                menuCommand.getPath(), menuCommand.getComponent(), menuCommand.getIcon(), menuCommand.getSortValue(),
                IDict.getByCode(BooleanEnum.class, menuCommand.getIsPublic()), menuCommand.getDescribe());
    }
    
    /**
     * 转换.
     *
     * @param menuCommand
     * @return
     */
    default Menu toMenu(MenuUpdateCommand menuCommand) {
        return new Menu(new MenuId(menuCommand.getId()), new MenuName(menuCommand.getMenuName()),
                new MenuId(menuCommand.getParentId()), menuCommand.getPath(), menuCommand.getComponent(),
                menuCommand.getIcon(), menuCommand.getSortValue(),
                IDict.getByCode(BooleanEnum.class, menuCommand.getIsPublic()), null, menuCommand.getDescribe());
    }
    
    @Mappings({@Mapping(source = "menuName", target = "meta.title"),
            @Mapping(source = "icon", target = "meta.icon")})
    VueRouterDTO toRouterDto(SysMenuDO menu);
    
    List<VueRouterDTO> toRouterDto(List<SysMenuDO> menuList);
}
