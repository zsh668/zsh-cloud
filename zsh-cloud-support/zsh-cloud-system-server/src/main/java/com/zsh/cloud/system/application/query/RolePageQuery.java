package com.zsh.cloud.system.application.query;

import com.zsh.cloud.common.core.dto.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色分页Query.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/31 10:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("角色分页对象")
public class RolePageQuery extends PageQuery {
    
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
}
