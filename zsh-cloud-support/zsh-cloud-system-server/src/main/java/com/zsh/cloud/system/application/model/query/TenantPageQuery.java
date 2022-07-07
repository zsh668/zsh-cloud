package com.zsh.cloud.system.application.model.query;

import com.zsh.cloud.common.core.dto.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 租户分页Query.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/26 11:15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("租户分页对象")
public class TenantPageQuery extends PageQuery {
    
    /**
     * 租户编码.
     */
    @ApiModelProperty(value = "租户编码", notes = "模糊匹配")
    private String tenantCode;
    
    /**
     * 租户名称.
     */
    @ApiModelProperty(value = "租户名称", notes = "模糊匹配")
    private String tenantName;
}
