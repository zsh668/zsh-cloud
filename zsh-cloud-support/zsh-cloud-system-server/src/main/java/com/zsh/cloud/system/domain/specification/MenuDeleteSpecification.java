package com.zsh.cloud.system.domain.specification;

import com.zsh.cloud.common.core.domain.specification.AbstractSpecification;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.ServiceAssert;
import com.zsh.cloud.system.domain.model.menu.Menu;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysMenuDO;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysResourceDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysMenuMapper;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysResourceMapper;

/**
 * 菜单删除Specification.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/16 10:05
 */
public class MenuDeleteSpecification extends AbstractSpecification<Menu> {
    
    private SysMenuMapper sysMenuMapper;
    
    private SysResourceMapper sysResourceMapper;
    
    public MenuDeleteSpecification(SysMenuMapper sysMenuMapper, SysResourceMapper sysResourceMapper) {
        this.sysMenuMapper = sysMenuMapper;
        this.sysResourceMapper = sysResourceMapper;
    }
    
    @Override
    public boolean isSatisfiedBy(Menu menu) {
        // 是否存在 下级菜单
        Long count = sysMenuMapper.selectCount(SysMenuDO::getParentId, menu.getMenuId().getId());
        ServiceAssert.notTrue(count > 0, ServiceErrorCode.MENU_VERIFICATION_ERROR.getCode(), "当前菜单存在下级菜单无法删除或禁用");
        // 当前菜单是否存在 资源
        count = sysResourceMapper.selectCount(SysResourceDO::getMenuId, menu.getMenuId().getId());
        ServiceAssert.notTrue(count > 0, ServiceErrorCode.MENU_VERIFICATION_ERROR.getCode(), "当前菜单存在资源无法删除或禁用");
        return true;
    }
}
