package com.zsh.cloud.system.application.model.query;

import com.zsh.cloud.common.core.dto.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 组织分页Query.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/14 18:14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("组织分页查询对象")
public class OrgPageQuery extends PageQuery {
    
    /**
     * 组织名称
     */
    @ApiModelProperty(value = "组织名称")
    private String orgName;
}
