package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.common.core.util.ListUtils;
import com.zsh.cloud.system.application.MenuQueryService;
import com.zsh.cloud.system.application.ResourceQueryService;
import com.zsh.cloud.system.application.assembler.MenuDtoAssembler;
import com.zsh.cloud.system.application.model.dto.MenuDTO;
import com.zsh.cloud.system.application.model.dto.MenuResourceTreeDTO;
import com.zsh.cloud.system.application.model.dto.MenuTreeDTO;
import com.zsh.cloud.system.application.model.dto.ResourceDTO;
import com.zsh.cloud.system.application.model.dto.VueRouterDTO;
import com.zsh.cloud.system.application.model.query.MenuPageQuery;
import com.zsh.cloud.system.application.model.query.RouterQuery;
import com.zsh.cloud.system.domain.model.menu.Menu;
import com.zsh.cloud.system.domain.model.menu.MenuId;
import com.zsh.cloud.system.domain.model.menu.MenuRepository;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysMenuDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysMenuMapper;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysRoleAuthorityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    
    @Autowired
    private ResourceQueryService resourceQueryService;
    
    @Override
    public List<MenuTreeDTO> queryList(MenuPageQuery menuPageQuery) {
        List<SysMenuDO> menuList = sysMenuMapper.selectList(menuPageQuery);
        List<MenuTreeDTO> menus = menuDtoAssembler.toDto(menuList);
        List<MenuTreeDTO> result = ListUtils.treeify(menus);
        buildParentStatus(result, null);
        buildLevel(result, 1);
        return result;
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
        List<VueRouterDTO> result = ListUtils.treeify(menus);
        buildRouterLevel(result, 1);
        return result;
    }
    
    @Override
    public List<MenuResourceTreeDTO> queryMenuResourceList() {
        List<SysMenuDO> menuList = sysMenuMapper.selectList();
        List<MenuResourceTreeDTO> menus = menuDtoAssembler.toResourceDto(menuList);
        buildResource(menus);
        return ListUtils.treeify(menus);
    }
    
    /**
     * 构建父菜单状态
     *
     * @param list
     * @param status
     */
    private void buildParentStatus(List<MenuTreeDTO> list, Boolean status) {
        for (MenuTreeDTO menuTreeDTO : list) {
            menuTreeDTO.setParentStatus(status);
            if (!CollectionUtils.isEmpty(menuTreeDTO.getChildren())) {
                Boolean parentIsEnable = menuTreeDTO.getStatus();
                buildParentStatus(menuTreeDTO.getChildren(), parentIsEnable);
            }
        }
    }
    
    /**
     * 构建菜单级别
     *
     * @param list
     * @param level
     */
    private void buildLevel(List<MenuTreeDTO> list, int level) {
        for (MenuTreeDTO menuTreeDTO : list) {
            menuTreeDTO.setLevel(level);
            if (!CollectionUtils.isEmpty(menuTreeDTO.getChildren())) {
                int parentLevel = menuTreeDTO.getLevel();
                buildLevel(menuTreeDTO.getChildren(), parentLevel + 1);
            }
        }
    }
    
    /**
     * 构建菜单级别
     *
     * @param list
     * @param level
     */
    private void buildRouterLevel(List<VueRouterDTO> list, int level) {
        for (VueRouterDTO vueRouterDTO : list) {
            vueRouterDTO.setLevel(level);
            if (!CollectionUtils.isEmpty(vueRouterDTO.getChildren())) {
                int parentLevel = vueRouterDTO.getLevel();
                buildRouterLevel(vueRouterDTO.getChildren(), parentLevel + 1);
            }
        }
    }
    
    /**
     * 构建资源信息
     *
     * @param treeList
     */
    public void buildResource(List<MenuResourceTreeDTO> treeList) {
        for (MenuResourceTreeDTO menuTreeDTO : treeList) {
            List<ResourceDTO> resources = resourceQueryService.queryList(menuTreeDTO.getId());
            menuTreeDTO.setResources(resources);
        }
    }
}
