package com.zsh.cloud.system.application.model.query;

import com.zsh.cloud.common.core.dto.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户组分页Query.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/31 10:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("用户组分页对象")
public class UserGroupPageQuery extends PageQuery {
    
    /**
     * 用户组名称.
     */
    @ApiModelProperty(value = "用户组名称")
    private String groupName;
    
    /**
     * 角色id.
     */
    @ApiModelProperty(value = "角色id")
    private String roleId;
    
    /**
     * 状态.
     */
    @ApiModelProperty(value = "状态")
    private Boolean status;
}
