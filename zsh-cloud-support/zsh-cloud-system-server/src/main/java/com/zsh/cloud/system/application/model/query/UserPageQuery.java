package com.zsh.cloud.system.application.model.query;

import com.zsh.cloud.common.core.dto.PageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户分页Query.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/29 14:47
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("用户分页对象")
public class UserPageQuery extends PageQuery {
    
    /**
     * 账号.
     */
    @ApiModelProperty(value = "账号", example = "ceshi", notes = "模糊匹配")
    private String account;
    
    /**
     * 姓名.
     */
    @ApiModelProperty(value = "姓名", example = "张三", notes = "模糊匹配")
    private String userName;
    
    /**
     * 组织ID.
     */
    @ApiModelProperty(value = "组织ID", example = "1000", notes = "同时筛选子组织")
    private String orgId;
    
    /**
     * 状态.
     */
    @ApiModelProperty(value = "状态")
    private Boolean status;
}
