package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.system.application.MenuApplicationService;
import com.zsh.cloud.system.application.assembler.MenuDtoAssembler;
import com.zsh.cloud.system.application.command.MenuCreateCommand;
import com.zsh.cloud.system.application.command.MenuUpdateCommand;
import com.zsh.cloud.system.domain.model.menu.Menu;
import com.zsh.cloud.system.domain.model.menu.MenuId;
import com.zsh.cloud.system.domain.model.menu.MenuRepository;
import com.zsh.cloud.system.domain.specification.MenuCreateSpecification;
import com.zsh.cloud.system.domain.specification.MenuDeleteSpecification;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysMenuMapper;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单应用服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 16:35
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MenuApplicationServiceImpl implements MenuApplicationService {
    
    @Autowired
    private MenuRepository menuRepository;
    
    @Autowired
    private SysMenuMapper sysMenuMapper;
    
    @Autowired
    private SysResourceMapper sysResourceMapper;
    
    @Autowired
    private MenuDtoAssembler menuDtoAssembler;
    
    @Override
    public void save(MenuCreateCommand menuCommand) {
        Menu menu = menuDtoAssembler.toMenu(menuCommand);
        MenuCreateSpecification specification = new MenuCreateSpecification(menuRepository);
        specification.isSatisfiedBy(menu);
        menuRepository.store(menu);
    }
    
    @Override
    public void update(MenuUpdateCommand menuCommand) {
        Menu menu = menuDtoAssembler.toMenu(menuCommand);
        MenuCreateSpecification specification = new MenuCreateSpecification(menuRepository);
        specification.isSatisfiedBy(menu);
        menuRepository.store(menu);
    }
    
    @Override
    public void deleteBatch(List<String> ids) {
        List<MenuId> menuIds = new ArrayList<>();
        MenuDeleteSpecification specification = new MenuDeleteSpecification(sysMenuMapper, sysResourceMapper);
        ids.forEach(id -> {
            MenuId menuId = new MenuId(id);
            Menu menu = menuRepository.find(menuId);
            specification.isSatisfiedBy(menu);
            menuIds.add(menuId);
        });
        menuRepository.remove(menuIds);
    }
    
    @Override
    public void disable(String id) {
        Menu menu = menuRepository.find(new MenuId(id));
        menu.disable();
        menuRepository.store(menu);
    }
}
