package com.zsh.cloud.system.application.assembler;

import com.zsh.cloud.system.application.dto.MenuTreeDTO;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysMenuDO;
import org.mapstruct.Mapper;

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
}
