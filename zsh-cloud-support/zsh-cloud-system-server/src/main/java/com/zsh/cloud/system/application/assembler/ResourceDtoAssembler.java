package com.zsh.cloud.system.application.assembler;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.command.ResourceCreateCommand;
import com.zsh.cloud.system.application.command.ResourceUpdateCommand;
import com.zsh.cloud.system.application.dto.ResourceDTO;
import com.zsh.cloud.system.application.dto.ResourcePageDTO;
import com.zsh.cloud.system.domain.model.menu.MenuId;
import com.zsh.cloud.system.domain.model.resource.Resource;
import com.zsh.cloud.system.domain.model.resource.ResourceCode;
import com.zsh.cloud.system.domain.model.resource.ResourceId;
import com.zsh.cloud.system.domain.model.resource.ResourceName;
import com.zsh.cloud.system.domain.model.resource.ResourceUrl;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysResourceDO;
import org.mapstruct.Mapper;
import org.springframework.http.HttpMethod;

import java.util.List;

/**
 * 资源Assembler.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 16:49
 */
@Mapper(componentModel = "spring")
public interface ResourceDtoAssembler {
    
    /**
     * resource转换.
     *
     * @param resource
     * @return
     */
    ResourcePageDTO toDto(SysResourceDO resource);
    
    /**
     * resource转换.(ResourcePageDTO toDto())
     *
     * @param resource
     * @return
     */
    List<ResourcePageDTO> toDto(List<SysResourceDO> resource);
    
    /**
     * resource转换.(List toDto())
     *
     * @param resource
     * @return
     */
    Page<ResourcePageDTO> toDto(Page<SysResourceDO> resource);
    
    /**
     * 转换.
     *
     * @param resource
     * @return
     */
    default ResourceDTO fromResource(Resource resource) {
        if (resource == null) {
            return null;
        }
        ResourceDTO resourceDto = new ResourceDTO();
        resourceDto.setId(resource.getResourceId() == null ? "" : resource.getResourceId().getId())
                .setResourceName(resource.getResourceName() == null ? "" : resource.getResourceName().getName())
                .setResourceCode(resource.getResourceCode() == null ? "" : resource.getResourceCode().getCode())
                .setMethod(resource.getMethod() == null ? "" : resource.getMethod().name())
                .setStatus(resource.getStatus() == null ? null : resource.getStatus().getCode())
                .setUrl(resource.getResourceUrl() == null ? "" : resource.getResourceUrl().getUrl())
                .setDescribe(resource.getDescribe());
        return resourceDto;
    }
    
    /**
     * 转换.
     *
     * @param resourceCommand
     * @return
     */
    default Resource toResource(ResourceCreateCommand resourceCommand) {
        return new Resource(new ResourceCode(resourceCommand.getResourceCode()),
                new ResourceName(resourceCommand.getResourceName()), new MenuId(resourceCommand.getMenuId()),
                HttpMethod.resolve(resourceCommand.getMethod().toUpperCase()),
                new ResourceUrl(resourceCommand.getUrl()), resourceCommand.getDescribe());
    }
    
    /**
     * 转换.
     *
     * @param resourceCommand
     * @return
     */
    default Resource toResource(ResourceUpdateCommand resourceCommand) {
        return new Resource(new ResourceId(resourceCommand.getId()),
                new ResourceCode(resourceCommand.getResourceCode()),
                new ResourceName(resourceCommand.getResourceName()), new MenuId(resourceCommand.getMenuId()),
                HttpMethod.resolve(resourceCommand.getMethod().toUpperCase()),
                new ResourceUrl(resourceCommand.getUrl()), null, resourceCommand.getDescribe());
    }
}
