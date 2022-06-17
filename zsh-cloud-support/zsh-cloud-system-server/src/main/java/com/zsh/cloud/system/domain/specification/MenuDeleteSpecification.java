package com.zsh.cloud.system.domain.specification;

import com.zsh.cloud.common.core.domain.specification.AbstractSpecification;
import com.zsh.cloud.system.domain.model.menu.Menu;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysMenuMapper;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysUserMapper;

/**
 * 菜单删除Specification.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/16 10:05
 */
public class MenuDeleteSpecification extends AbstractSpecification<Menu> {
    
    private SysMenuMapper sysMenuMapper;
    
    private SysUserMapper sysUserMapper;
    
    public MenuDeleteSpecification(SysMenuMapper sysMenuMapper, SysUserMapper sysUserMapper) {
        this.sysMenuMapper = sysMenuMapper;
        this.sysUserMapper = sysUserMapper;
    }
    
    @Override
    public boolean isSatisfiedBy(Menu menu) {
        // 是否存在 下级菜单
        //        Long count = sysMenuMapper.selectCount(SysMenuDO::getId, menu.getParentId().getId());
        //        ServiceAssert.notTrue(count > 0, ServiceErrorCode.ORG_VERIFICATION_ERROR.getCode(), "当前菜单存在下级菜单无法删除或禁用");
        //        // 当前菜单是否存在 用户
        //        count = sysUserMapper.selectCount(SysUserDO::getMenuId, menu.getMenuId().getId());
        //        ServiceAssert.notTrue(count > 0, ServiceErrorCode.ORG_VERIFICATION_ERROR.getCode(), "当前菜单存在用户无法删除或禁用");
        return true;
    }
}
