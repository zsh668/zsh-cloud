package com.zsh.cloud.system.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.system.domain.model.menu.MenuRepository;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysMenuDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysMenuMapper;
import org.springframework.stereotype.Repository;

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
    
}
