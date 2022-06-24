package com.zsh.cloud.system.application.impl;

import com.zsh.cloud.system.application.ResourceApplicationService;
import com.zsh.cloud.system.application.assembler.ResourceDtoAssembler;
import com.zsh.cloud.system.application.command.ResourceCreateCommand;
import com.zsh.cloud.system.application.command.ResourceUpdateCommand;
import com.zsh.cloud.system.domain.model.menu.MenuRepository;
import com.zsh.cloud.system.domain.model.resource.Resource;
import com.zsh.cloud.system.domain.model.resource.ResourceId;
import com.zsh.cloud.system.domain.model.resource.ResourceRepository;
import com.zsh.cloud.system.domain.specification.ResourceCreateSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源应用服务实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 16:35
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceApplicationServiceImpl implements ResourceApplicationService {
    
    @Autowired
    private ResourceRepository resourceRepository;
    
    @Autowired
    private MenuRepository menuRepository;
    
    @Autowired
    private ResourceDtoAssembler resourceDtoAssembler;
    
    @Override
    public void save(ResourceCreateCommand resourceCommand) {
        Resource resource = resourceDtoAssembler.toResource(resourceCommand);
        ResourceCreateSpecification specification = new ResourceCreateSpecification(resourceRepository, menuRepository);
        specification.isSatisfiedBy(resource);
        resourceRepository.store(resource);
    }
    
    @Override
    public void update(ResourceUpdateCommand resourceCommand) {
        Resource resource = resourceDtoAssembler.toResource(resourceCommand);
        ResourceCreateSpecification specification = new ResourceCreateSpecification(resourceRepository, menuRepository);
        specification.isSatisfiedBy(resource);
        resourceRepository.store(resource);
    }
    
    @Override
    public void deleteBatch(List<String> ids) {
        List<ResourceId> resourceIds = new ArrayList<>();
        ids.forEach(id -> resourceIds.add(new ResourceId(id)));
        resourceRepository.remove(resourceIds);
    }
    
    @Override
    public void disable(String id) {
        Resource resource = resourceRepository.find(new ResourceId(id));
        resource.disable();
        resourceRepository.store(resource);
    }
}
