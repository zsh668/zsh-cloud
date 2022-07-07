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
 * 岗位信息DTO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/10 15:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "StationDTO", description = "岗位信息")
public class StationDTO extends DTO {
    
    /**
     * id.
     */
    @ApiModelProperty(value = "岗位id")
    private String id;
    
    /**
     * 名称.
     */
    @ApiModelProperty(value = "岗位名称")
    private String stationName;
    
    /**
     * 组织ID.
     */
    @ApiModelProperty(value = "组织ID")
    private String orgId;
    
    /**
     * 组织名称.
     */
    @ApiModelProperty(value = "组织名称")
    @Translate(translator = ServiceImplTranslator.class, dataSource = OrgRepositoryImpl.class, from = "orgId", param = "orgName")
    private String orgName;
    
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
