package com.zsh.cloud.system.application.assembler;

import com.zsh.cloud.system.application.dto.OrgDTO;
import com.zsh.cloud.system.application.dto.OrgTreeDTO;
import com.zsh.cloud.system.domain.model.org.Org;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysOrgDO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 组织Assembler.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/14 18:28
 */
@Mapper(componentModel = "spring")
public interface OrgDtoAssembler {
    
    OrgTreeDTO toDto(SysOrgDO org);
    
    List<OrgTreeDTO> toDto(List<SysOrgDO> orgList);
    
    /**
     * 转换.
     *
     * @param org
     * @return
     */
    default OrgDTO fromOrg(Org org) {
        if (org == null) {
            return null;
        }
        OrgDTO orgDto = new OrgDTO();
        orgDto.setId(org.getOrgId() == null ? null : org.getOrgId().getId());
        orgDto.setOrgName(org.getOrgName() == null ? null : org.getOrgName().getName());
        orgDto.setParentId(org.getParentId() == null ? null : org.getParentId().getId());
        orgDto.setSortValue(org.getSortValue());
        orgDto.setStatus(org.getStatus() == null ? null : org.getStatus().getCode());
        orgDto.setDescribe(org.getDescribe());
        return orgDto;
    }
}
