package com.zsh.cloud.system.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.tenant.entity.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 组织DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/26 10:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_org")
public class SysOrgDO extends TenantBaseDO {
    
    /**
     * 名称.
     */
    private String orgName;
    
    /**
     * 简称.
     */
    private String abbreviation;
    
    /**
     * 父ID.
     */
    private String parentId;
    
    /**
     * 部门类型 1为分公司，2为一级转运中心 3为二级转运中心 4为网点.
     */
    private Integer orgType;
    
    /**
     * 省.
     */
    private String provinceId;
    
    /**
     * 市.
     */
    private String cityId;
    
    /**
     * 区.
     */
    private String countyId;
    
    /**
     * 地址.
     */
    private String address;
    
    /**
     * 联系电话.
     */
    private String contractNumber;
    
    /**
     * 负责人id.
     */
    private String managerId;
    
    /**
     * 树结构.
     */
    private String treePath;
    
    /**
     * 排序.
     */
    private Integer sortValue;
    
    /**
     * 状态 1启用，0禁用.
     */
    private Integer status;
    
    /**
     * 功能描述.
     */
    @TableField(value = "describe_", condition = SqlCondition.LIKE)
    private String describe;
}
