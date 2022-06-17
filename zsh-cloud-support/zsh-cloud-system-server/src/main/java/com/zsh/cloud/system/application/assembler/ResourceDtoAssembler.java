package com.zsh.cloud.system.application.assembler;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.system.application.dto.ResourcePageDTO;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysResourceDO;
import org.mapstruct.Mapper;

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
}
