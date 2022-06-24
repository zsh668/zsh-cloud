package com.zsh.cloud.system.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.system.domain.model.menu.MenuId;
import com.zsh.cloud.system.domain.model.resource.Resource;
import com.zsh.cloud.system.domain.model.resource.ResourceCode;
import com.zsh.cloud.system.domain.model.resource.ResourceId;
import com.zsh.cloud.system.domain.model.resource.ResourceRepository;
import com.zsh.cloud.system.infrastructure.persistence.converter.ResourceConverter;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysResourceDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysResourceMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源-Repository实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 13:44
 */
@Repository
public class ResourceRepositoryImpl extends ServiceImpl<SysResourceMapper, SysResourceDO>
        implements ResourceRepository, IService<SysResourceDO> {
    
    @Override
    public Resource find(ResourceId resourceId) {
        SysResourceDO sysResourceDO = baseMapper.selectById(resourceId.getId());
        return ResourceConverter.toResource(sysResourceDO);
    }
    
    @Override
    public List<Resource> find(ResourceCode resourceCode) {
        List<SysResourceDO> resourceDOList = baseMapper.selectList(SysResourceDO::getResourceCode,
                resourceCode.getCode());
        if (CollectionUtils.isEmpty(resourceDOList)) {
            return null;
        }
        List<Resource> resources = new ArrayList<>();
        resourceDOList.forEach(sysResourceDO -> resources.add(ResourceConverter.toResource(sysResourceDO)));
        return resources;
    }
    
    @Override
    public List<Resource> queryList(MenuId menuId) {
        List<SysResourceDO> resourceDOList = baseMapper.selectList(SysResourceDO::getMenuId, menuId.getId());
        if (CollectionUtils.isEmpty(resourceDOList)) {
            return null;
        }
        List<Resource> resources = new ArrayList<>();
        resourceDOList.forEach(sysResourceDO -> resources.add(ResourceConverter.toResource(sysResourceDO)));
        return resources;
    }
    
    @Override
    public ResourceId store(Resource resource) {
        SysResourceDO sysResourceDO = ResourceConverter.fromResource(resource);
        this.saveOrUpdate(sysResourceDO);
        return new ResourceId(sysResourceDO.getId());
    }
    
    @Override
    public void remove(List<ResourceId> resourceIds) {
        List<String> ids = new ArrayList<>();
        resourceIds.forEach(resourceId -> ids.add(resourceId.getId()));
        this.removeByIds(ids);
    }
}
