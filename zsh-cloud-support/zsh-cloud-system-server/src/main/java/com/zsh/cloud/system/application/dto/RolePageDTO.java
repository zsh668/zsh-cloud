package com.zsh.cloud.system.application.dto;

import com.zsh.cloud.common.core.dto.DTO;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.web.translate.Translate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 角色分页信息DTO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/31 10:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
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
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
}
