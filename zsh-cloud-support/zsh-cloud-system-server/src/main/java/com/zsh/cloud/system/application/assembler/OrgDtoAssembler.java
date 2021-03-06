package com.zsh.cloud.system.application.assembler;

import com.zsh.cloud.system.application.model.command.OrgCreateCommand;
import com.zsh.cloud.system.application.model.command.OrgUpdateCommand;
import com.zsh.cloud.system.application.model.dto.OrgDTO;
import com.zsh.cloud.system.application.model.dto.OrgTreeDTO;
import com.zsh.cloud.system.domain.model.org.Org;
import com.zsh.cloud.system.domain.model.org.OrgId;
import com.zsh.cloud.system.domain.model.org.OrgName;
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
    
    /**
     * 转换.
     *
     * @param org
     * @return
     */
    OrgTreeDTO toDto(SysOrgDO org);
    
    /**
     * 转换.
     *
     * @param orgList
     * @return
     */
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
    
    /**
     * 转换.
     *
     * @param orgCommand
     * @return
     */
    default Org toOrg(OrgCreateCommand orgCommand) {
        return new Org(new OrgName(orgCommand.getOrgName()), new OrgId(orgCommand.getParentId()),
                orgCommand.getSortValue(), orgCommand.getDescribe());
    }
    
    /**
     * 转换.
     *
     * @param orgCommand
     * @return
     */
    default Org toOrg(OrgUpdateCommand orgCommand) {
        return new Org(new OrgId(orgCommand.getId()), new OrgName(orgCommand.getOrgName()),
                new OrgId(orgCommand.getParentId()), orgCommand.getSortValue(), null, orgCommand.getDescribe());
    }
}
