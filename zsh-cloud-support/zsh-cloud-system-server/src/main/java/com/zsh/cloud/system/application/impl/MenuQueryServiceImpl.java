package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.common.core.util.ListUtils;
import com.zsh.cloud.system.application.MenuQueryService;
import com.zsh.cloud.system.application.assembler.MenuDtoAssembler;
import com.zsh.cloud.system.application.dto.MenuDTO;
import com.zsh.cloud.system.application.dto.MenuTreeDTO;
import com.zsh.cloud.system.application.query.MenuPageQuery;
import com.zsh.cloud.system.domain.model.menu.Menu;
import com.zsh.cloud.system.domain.model.menu.MenuId;
import com.zsh.cloud.system.domain.model.menu.MenuRepository;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysMenuDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单查询服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 16:35
 */
@Service
public class MenuQueryServiceImpl implements MenuQueryService {
    
    @Autowired
    private SysMenuMapper sysMenuMapper;
    
    @Autowired
    private MenuRepository menuRepository;
    
    @Autowired
    private MenuDtoAssembler menuDtoAssembler;
    
    @Override
    public List<MenuTreeDTO> queryList(MenuPageQuery menuPageQuery) {
        List<SysMenuDO> menuList = sysMenuMapper.selectList(menuPageQuery);
        List<MenuTreeDTO> menus = menuDtoAssembler.toDto(menuList);
        return ListUtils.treeify(menus);
    }
    
    @Override
    public MenuDTO find(String id) {
        Menu menu = menuRepository.find(new MenuId(id));
        return menuDtoAssembler.fromMenu(menu);
    }
}
