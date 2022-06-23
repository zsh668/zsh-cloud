package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.mybatis.util.Wraps;
import com.zsh.cloud.system.application.ResourceQueryService;
import com.zsh.cloud.system.application.assembler.ResourceDtoAssembler;
import com.zsh.cloud.system.application.dto.ResourceDTO;
import com.zsh.cloud.system.application.dto.ResourcePageDTO;
import com.zsh.cloud.system.application.query.ResourcePageQuery;
import com.zsh.cloud.system.domain.model.resource.Resource;
import com.zsh.cloud.system.domain.model.resource.ResourceId;
import com.zsh.cloud.system.domain.model.resource.ResourceRepository;
import com.zsh.cloud.system.domain.model.user.UserId;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysResourceDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 资源查询服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/29 11:04
 */
@Service
public class ResourceQueryServiceImpl implements ResourceQueryService {
    
    @Autowired
    private SysResourceMapper sysResourceMapper;
    
    @Autowired
    private ResourceRepository resourceRepository;
    
    @Autowired
    private ResourceDtoAssembler resourceDtoAssembler;
    
    @Override
    public Set<String> getPermissionCodes(String userId) {
        // 获取权限资源
        List<SysResourceDO> sysPermissionDOList = getSysResourceDOList(userId);
        return sysPermissionDOList.stream().filter(Objects::nonNull).map(SysResourceDO::getResourceCode)
                .collect(Collectors.toSet());
    }
    
    @Override
    public Page<ResourcePageDTO> queryPage(ResourcePageQuery resourcePageQuery) {
        Page<SysResourceDO> page = sysResourceMapper.selectPage(resourcePageQuery);
        return resourceDtoAssembler.toDto(page);
    }
    
    @Override
    public ResourceDTO find(String id) {
        Resource resource = resourceRepository.find(new ResourceId(id));
        return resourceDtoAssembler.fromResource(resource);
    }
    
    /**
     * 获取用户资源
     *
     * @param userId
     * @return
     */
    private List<SysResourceDO> getSysResourceDOList(String userId) {
        List<SysResourceDO> sysResourceDOList;
        if (new UserId(userId).isSysAdmin()) {
            sysResourceDOList = sysResourceMapper.selectList(
                    Wraps.<SysResourceDO>lbQ().eq(SysResourceDO::getStatus, StatusEnum.ENABLE.getCode()));
        } else {
            sysResourceDOList = sysResourceMapper.queryResourceByUserId(userId);
        }
        return sysResourceDOList;
    }
}
