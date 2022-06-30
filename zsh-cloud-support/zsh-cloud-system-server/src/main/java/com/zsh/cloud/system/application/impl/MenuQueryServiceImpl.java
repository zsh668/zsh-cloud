package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.common.core.util.ListUtils;
import com.zsh.cloud.system.application.MenuQueryService;
import com.zsh.cloud.system.application.assembler.MenuDtoAssembler;
import com.zsh.cloud.system.application.dto.MenuDTO;
import com.zsh.cloud.system.application.dto.MenuTreeDTO;
import com.zsh.cloud.system.application.dto.VueRouterDTO;
import com.zsh.cloud.system.application.query.MenuPageQuery;
import com.zsh.cloud.system.application.query.RouterQuery;
import com.zsh.cloud.system.domain.model.menu.AuthorizeTypeEnum;
import com.zsh.cloud.system.domain.model.menu.Menu;
import com.zsh.cloud.system.domain.model.menu.MenuId;
import com.zsh.cloud.system.domain.model.menu.MenuRepository;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysMenuDO;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysRoleAuthorityDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysMenuMapper;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysRoleAuthorityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
    
    @Autowired
    private SysRoleAuthorityMapper sysRoleAuthorityMapper;
    
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
    
    @Override
    public List<VueRouterDTO> queryRouterList(RouterQuery routerQuery) {
        List<SysMenuDO> menuList;
        // 是否是系统管理员
        if (new UserId(routerQuery.getUserId()).isSysAdmin()) {
            menuList = sysMenuMapper.selectList();
        } else {
            Set<String> menuIds = sysRoleAuthorityMapper.queryUserMenu(routerQuery.getUserId());
            menuList = sysMenuMapper.queryByMenuIds(menuIds, routerQuery.getGroup());
        }
        List<VueRouterDTO> menus = menuDtoAssembler.toRouterDto(menuList);
        return ListUtils.treeify(menus);
    }
}
