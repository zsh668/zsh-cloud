package com.zsh.cloud.system.application.model.command;

import com.zsh.cloud.common.core.dto.Command;
import com.zsh.cloud.common.mybatis.datascope.enums.DataScopeTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 更新角色Command.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/31 10:49
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "更新角色", description = "更新角色")
public class RoleUpdateCommand extends Command {
    
    /**
     * 角色ID.
     */
    @ApiModelProperty(value = "角色ID")
    @NotEmpty(message = "角色ID不能为空")
    private String id;
    
    /**
     * 角色编码.
     */
    @ApiModelProperty(value = "角色编码")
    @NotEmpty(message = "角色编码不能为空")
    @Length(max = 20, message = "角色编码长度不能超过20")
    private String roleCode;
    
    /**
     * 角色名称.
     */
    @ApiModelProperty(value = "角色名称")
    @NotEmpty(message = "角色名称不能为空")
    @Length(max = 30, message = "角色名称长度不能超过30")
    private String roleName;
    
    /**
     * 数据权限类型 #DataScopeTypeEnum{ALL:1,全部;THIS_LEVEL:2,本级;THIS_LEVEL_CHILDREN:3,本级以及子级;CUSTOMIZE:4,自定义;SELF:5,个人;}.
     */
    @ApiModelProperty(value = "数据权限类型")
    @NotNull(message = "数据权限类型不能为空")
    private DataScopeTypeEnum dsType;
    
    /**
     * 互斥角色.
     */
    @ApiModelProperty(value = "互斥角色")
    private String repel;
    
    /**
     * 功能描述.
     */
    @ApiModelProperty(value = "功能描述")
    @Length(max = 100, message = "功能描述长度不能超过100")
    private String describe;
    
    /**
     * 关联的组织id.
     */
    @ApiModelProperty(value = "关联的组织id")
    private Set<String> orgList;
}
