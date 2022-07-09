package com.zsh.cloud.system.infrastructure.persistence.repository;

import cn.hutool.core.text.StrPool;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.system.domain.model.org.Org;
import com.zsh.cloud.system.domain.model.org.OrgId;
import com.zsh.cloud.system.domain.model.org.OrgName;
import com.zsh.cloud.system.domain.model.org.OrgRepository;
import com.zsh.cloud.system.infrastructure.persistence.converter.OrgConverter;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysOrgDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysOrgMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 组织-Repository实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 13:44
 */
@Repository
public class OrgRepositoryImpl extends ServiceImpl<SysOrgMapper, SysOrgDO>
        implements OrgRepository, IService<SysOrgDO> {
    
    @Override
    public Org find(OrgId orgId) {
        SysOrgDO sysOrgDO = baseMapper.selectById(orgId.getId());
        return OrgConverter.toOrg(sysOrgDO);
    }
    
    @Override
    public List<Org> find(OrgName orgName) {
        List<SysOrgDO> sysOrgDOList = baseMapper.selectList(SysOrgDO::getOrgName, orgName.getName());
        if (CollectionUtils.isEmpty(sysOrgDOList)) {
            return null;
        }
        List<Org> orgs = new ArrayList<>();
        sysOrgDOList.forEach(sysOrgDO -> orgs.add(OrgConverter.toOrg(sysOrgDO)));
        return orgs;
    }
    
    @Override
    public List<Org> queryList(OrgId parentId) {
        List<SysOrgDO> sysOrgDOList;
        if (parentId == null) {
            sysOrgDOList = baseMapper.selectList();
        } else {
            sysOrgDOList = baseMapper.selectList(SysOrgDO::getParentId, parentId.getId());
        }
        List<Org> orgs = new ArrayList<>();
        if (CollectionUtils.isEmpty(sysOrgDOList)) {
            return orgs;
        }
        sysOrgDOList.forEach(sysOrgDO -> orgs.add(OrgConverter.toOrg(sysOrgDO)));
        return orgs;
    }
    
    @Override
    public OrgId store(Org org) {
        SysOrgDO sysOrgDO = OrgConverter.fromOrg(org);
        SysOrgDO parentOrg = baseMapper.selectById(org.getParentId().getId());
        if (parentOrg != null) {
            sysOrgDO.setTreePath(StringUtils.join(parentOrg.getTreePath(), parentOrg.getId(), StrPool.COMMA));
        } else {
            sysOrgDO.setTreePath(StrPool.COMMA);
        }
        this.saveOrUpdate(sysOrgDO);
        return new OrgId(sysOrgDO.getId());
    }
    
    @Override
    public void remove(List<OrgId> orgIds) {
        List<String> ids = new ArrayList<>();
        orgIds.forEach(orgId -> ids.add(orgId.getId()));
        this.removeByIds(ids);
    }
    
    @Override
    public List<Org> findChildren(OrgId orgId) {
        List<Org> orgs = new ArrayList<>();
        Org org = this.find(orgId);
        if (org == null) {
            return orgs;
        }
        orgs.add(org);
        findChildren(orgId, orgs);
        return orgs;
    }
    
    /**
     * 查询组织的全部子节点.
     *
     * @param orgId
     * @param orgs
     */
    private void findChildren(OrgId orgId, List<Org> orgs) {
        List<Org> children = this.queryList(orgId);
        orgs.addAll(children);
        children.forEach(org -> findChildren(org.getOrgId(), orgs));
    }
}
