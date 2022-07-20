package com.zsh.cloud.system.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.tenant.entity.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/26 10:14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
public class SysRoleDO extends TenantBaseDO {
    
    /**
     * 角色名称.
     */
    private String roleName;
    
    /**
     * 角色编码.
     */
    private String roleCode;
    
    /**
     * 是否内置角色.
     */
    private Boolean readonly;
    
    /**
     * 数据权限类型 1,全部;2,本级;3,本级以及子级;4,自定义;5,个人.
     */
    private Integer dsType;
    
    /**
     * 互斥角色.
     */
    private String repel;
    
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
