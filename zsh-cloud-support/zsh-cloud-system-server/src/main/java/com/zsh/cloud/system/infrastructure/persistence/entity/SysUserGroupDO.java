package com.zsh.cloud.system.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.tenant.entity.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户组DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/26 10:14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user_group")
public class SysUserGroupDO extends TenantBaseDO {
    
    /**
     * 用户组名称.
     */
    private String groupName;
    
    /**
     * 用户数量.
     */
    private Integer userCount;
    
    /**
     * 角色id.
     */
    private String roleId;
    
    /**
     * 状态 1启用，0禁用.
     */
    private Boolean status;
    
    /**
     * 功能描述.
     */
    @TableField(value = "describe_", condition = SqlCondition.LIKE)
    private String describe;
}
