package com.zsh.cloud.system.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.tenant.entity.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典类型DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/26 10:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_dict_type")
public class SysDictTypeDO extends TenantBaseDO {
    
    /**
     * 类型名称.
     */
    private String typeName;
    
    /**
     * 类型编码.
     */
    private String typeCode;
    
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
