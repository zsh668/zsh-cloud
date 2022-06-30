package com.zsh.cloud.system.application.query;

import com.zsh.cloud.common.core.dto.Query;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 路由Query.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/30 09:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("路由查询对象")
public class RouterQuery extends Query {
    
    /**
     * 菜单组.
     */
    @ApiModelProperty(value = "菜单组")
    private String group;
    
    /**
     * 用户ID.
     */
    @ApiModelProperty(value = "用户ID")
    private String userId;
}
