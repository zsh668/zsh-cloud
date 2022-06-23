package com.zsh.cloud.system.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.system.domain.model.menu.Menu;
import com.zsh.cloud.system.domain.model.menu.MenuId;
import com.zsh.cloud.system.domain.model.menu.MenuName;
import com.zsh.cloud.system.domain.model.menu.MenuRepository;
import com.zsh.cloud.system.infrastructure.persistence.converter.MenuConverter;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysMenuDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysMenuMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单-Repository实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 13:44
 */
@Repository
public class MenuRepositoryImpl extends ServiceImpl<SysMenuMapper, SysMenuDO>
        implements MenuRepository, IService<SysMenuDO> {
    
    @Override
    public Menu find(MenuId menuId) {
        SysMenuDO sysMenuDO = baseMapper.selectById(menuId.getId());
        return MenuConverter.toMenu(sysMenuDO);
    }
    
    @Override
    public List<Menu> find(MenuName menuName) {
        List<SysMenuDO> menuDOList = baseMapper.selectList(SysMenuDO::getMenuName, menuName.getName());
        if (CollectionUtils.isEmpty(menuDOList)) {
            return null;
        }
        List<Menu> menus = new ArrayList<>();
        menuDOList.forEach(sysMenuDO -> menus.add(MenuConverter.toMenu(sysMenuDO)));
        return menus;
    }
    
    @Override
    public List<Menu> queryList(MenuId parentId) {
        List<SysMenuDO> menuDOList = baseMapper.selectList(SysMenuDO::getParentId, parentId.getId());
        if (CollectionUtils.isEmpty(menuDOList)) {
            return null;
        }
        List<Menu> menus = new ArrayList<>();
        menuDOList.forEach(sysMenuDO -> menus.add(MenuConverter.toMenu(sysMenuDO)));
        return menus;
    }
    
    @Override
    public MenuId store(Menu menu) {
        SysMenuDO sysMenuDO = MenuConverter.fromMenu(menu);
        this.saveOrUpdate(sysMenuDO);
        return new MenuId(sysMenuDO.getId());
    }
    
    @Override
    public void remove(List<MenuId> menuIds) {
        List<String> ids = new ArrayList<>();
        menuIds.forEach(menuId -> ids.add(menuId.getId()));
        this.removeByIds(ids);
    }
}
