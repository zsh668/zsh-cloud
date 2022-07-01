package com.zsh.cloud.system.application.dto;

import com.zsh.cloud.common.core.dto.DTO;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.web.translate.Translate;
import com.zsh.cloud.system.application.translate.ServiceImplTranslator;
import com.zsh.cloud.system.infrastructure.persistence.repository.RoleRepositoryImpl;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户组分页信息DTO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/7 18:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "UserGroupPageDTO", description = "用户组分页信息")
public class UserGroupPageDTO extends DTO {
    
    /**
     * id.
     */
    @ApiModelProperty(value = "ID")
    private String id;
    
    /**
     * 用户组名称.
     */
    @ApiModelProperty(value = "用户组名称")
    private String groupName;
    
    /**
     * 用户数量.
     */
    @ApiModelProperty(value = "用户数量")
    private Integer userCount;
    
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
    
    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private String roleId;
    
    /**
     * 角色名称.
     */
    @ApiModelProperty(value = "角色名称")
    @Translate(translator = ServiceImplTranslator.class, dataSource = RoleRepositoryImpl.class, from = "roleId", param = "roleName")
    private String roleName;
}
