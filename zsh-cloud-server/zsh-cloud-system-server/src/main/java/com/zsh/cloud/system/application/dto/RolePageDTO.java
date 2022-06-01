package com.zsh.cloud.system.application.dto;

import com.zsh.cloud.common.core.dto.DTO;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.web.translate.Translate;
import com.zsh.cloud.system.application.translate.ServiceImplTranslator;
import com.zsh.cloud.system.infrastructure.persistence.repository.UserRepositoryImpl;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 角色分页信息DTO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/31 10:51
 */
@Data
@ApiModel(value = "RolePageDTO", description = "角色分页信息")
public class RolePageDTO extends DTO {
    
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
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;
    
    /**
     * 状态 1启用 0禁用.
     */
    @ApiModelProperty(value = "状态")
    @Translate(dataSource = StatusEnum.class, from = "status")
    private String statusDesc;
    
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人ID")
    private String createdBy;
    
    /**
     * 创建人姓名.
     */
    @ApiModelProperty(value = "创建人姓名")
    @Translate(translator = ServiceImplTranslator.class, dataSource = UserRepositoryImpl.class, from = "createdBy", param = "userName")
    private String createdName;
    
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
}