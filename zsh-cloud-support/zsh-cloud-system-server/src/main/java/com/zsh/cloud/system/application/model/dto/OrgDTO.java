package com.zsh.cloud.system.application.model.dto;

import com.zsh.cloud.common.core.dto.DTO;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.web.translate.Translate;
import com.zsh.cloud.system.application.translate.ServiceImplTranslator;
import com.zsh.cloud.system.infrastructure.persistence.repository.OrgRepositoryImpl;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 组织信息DTO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/14 14:27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "OrgDTO", description = "组织信息")
public class OrgDTO extends DTO {
    
    /**
     * id.
     */
    @ApiModelProperty(value = "组织ID")
    private String id;
    
    /**
     * 名称.
     */
    @ApiModelProperty(value = "组织名称")
    private String orgName;
    
    /**
     * 父id.
     */
    @ApiModelProperty(value = "组织父ID")
    private String parentId;
    
    /**
     * 父组织名称.
     */
    @ApiModelProperty(value = "父组织名称")
    @Translate(translator = ServiceImplTranslator.class, dataSource = OrgRepositoryImpl.class, from = "parentId", param = "orgName")
    private String parentName;
    
    /**
     * 排序.
     */
    @ApiModelProperty(value = "排序")
    private Integer sortValue;
    
    /**
     * 状态 1启用，0禁用.
     */
    @ApiModelProperty(value = "状态")
    private Boolean status;
    
    /**
     * 状态 1启用 0禁用.
     */
    @ApiModelProperty(value = "状态")
    @Translate(dataSource = StatusEnum.class, from = "status")
    private String statusDesc;
    
    /**
     * 功能描述.
     */
    @ApiModelProperty(value = "功能描述")
    private String describe;
}
