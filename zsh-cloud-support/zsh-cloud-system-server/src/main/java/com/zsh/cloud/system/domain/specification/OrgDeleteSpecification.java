package com.zsh.cloud.system.domain.specification;

import com.zsh.cloud.common.core.domain.specification.AbstractSpecification;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.ServiceAssert;
import com.zsh.cloud.system.domain.model.org.Org;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysOrgDO;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysOrgMapper;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysUserMapper;

/**
 * 组织删除Specification.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/16 10:05
 */
public class OrgDeleteSpecification extends AbstractSpecification<Org> {
    
    private SysOrgMapper sysOrgMapper;
    
    private SysUserMapper sysUserMapper;
    
    public OrgDeleteSpecification(SysOrgMapper sysOrgMapper, SysUserMapper sysUserMapper) {
        this.sysOrgMapper = sysOrgMapper;
        this.sysUserMapper = sysUserMapper;
    }
    
    @Override
    public boolean isSatisfiedBy(Org org) {
        // 是否存在 下级组织
        Long count = sysOrgMapper.selectCount(SysOrgDO::getId, org.getParentId().getId());
        ServiceAssert.notTrue(count > 0, ServiceErrorCode.ORG_VERIFICATION_ERROR.getCode(), "当前组织存在下级组织无法删除或禁用");
        // 当前组织是否存在 用户
        count = sysUserMapper.selectCount(SysUserDO::getOrgId, org.getOrgId().getId());
        ServiceAssert.notTrue(count > 0, ServiceErrorCode.ORG_VERIFICATION_ERROR.getCode(), "当前组织存在用户无法删除或禁用");
        return true;
    }
}
