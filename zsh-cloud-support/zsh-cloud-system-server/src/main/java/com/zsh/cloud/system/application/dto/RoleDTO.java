package com.zsh.cloud.system.application.dto;

import com.zsh.cloud.common.core.dto.DTO;
import com.zsh.cloud.common.core.enums.BooleanEnum;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.mybatis.datascope.enums.DataScopeTypeEnum;
import com.zsh.cloud.common.web.translate.Translate;
import com.zsh.cloud.system.application.translate.ServiceImplTranslator;
import com.zsh.cloud.system.infrastructure.persistence.repository.RoleRepositoryImpl;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 角色信息DTO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/31 10:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "RoleDTO", description = "角色信息")
public class RoleDTO extends DTO {
    
    /**
     * id
     */
    @ApiModelProperty(value = "角色ID")
    private String id;
    
    /**
     * 角色编码
     */
    @ApiModelProperty(value = "角色编码")
    private String roleCode;
    
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    
    /**
     * 互斥角色.
     */
    @ApiModelProperty(value = "互斥角色")
    private String repel;
    
    /**
     * 互斥角色.
     */
    @ApiModelProperty(value = "互斥角色")
    @Translate(translator = ServiceImplTranslator.class, dataSource = RoleRepositoryImpl.class, from = "repel", param = "roleName")
    private String repelName;
    
    /**
     * 数据权限范围
     */
    @ApiModelProperty(value = "数据权限范围")
    private DataScopeTypeEnum dsType;
    
    /**
     * 是否内置.
     */
    @ApiModelProperty(value = "是否内置")
    private Integer readonly;
    
    /**
     * 是否内置 1是 0否.
     */
    @ApiModelProperty(value = "是否内置")
    @Translate(dataSource = BooleanEnum.class, from = "readonly")
    private String readonlyDesc;
    
    /**
     * 状态
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
