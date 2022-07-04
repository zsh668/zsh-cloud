package com.zsh.cloud.system.infrastructure.persistence.repository;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.common.mybatis.util.Wraps;
import com.zsh.cloud.system.domain.model.role.RoleAuthorityRepository;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysRoleAuthorityDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysRoleAuthorityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色资源-Repository实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/4 11:57
 */
@Repository
public class RoleAuthorityRepositoryImpl extends ServiceImpl<SysRoleAuthorityMapper, SysRoleAuthorityDO>
        implements RoleAuthorityRepository, IService<SysRoleAuthorityDO> {
    
    @Override
    public void deleteByRoleId(String roleId) {
        baseMapper.delete(Wraps.<SysRoleAuthorityDO>lbQ().eq(SysRoleAuthorityDO::getRoleId, roleId));
    }
    
    @Override
    public void storeBatch(List<SysRoleAuthorityDO> list) {
        if (CollectionUtil.isNotEmpty(list)) {
            this.saveBatch(list);
        }
    }
}
