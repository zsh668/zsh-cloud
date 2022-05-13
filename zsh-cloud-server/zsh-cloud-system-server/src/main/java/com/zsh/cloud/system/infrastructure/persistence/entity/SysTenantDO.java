package com.zsh.cloud.system.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.mybatis.core.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 租户DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 09:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_tenant")
public class SysTenantDO extends BaseDO {
    
    /**
     * 租户编码.
     */
    private String tenantCode;
    
    /**
     * 租户名称.
     */
    private String tenantName;
    
    /**
     * 创建者ID.
     */
    private String creatorId;
    
    /**
     * 状态 1启用 0禁用.
     */
    private Integer status;
    
    /**
     * 功能描述。
     */
    @TableField(value = "describe_", condition = SqlCondition.LIKE)
    private String describe;
}
