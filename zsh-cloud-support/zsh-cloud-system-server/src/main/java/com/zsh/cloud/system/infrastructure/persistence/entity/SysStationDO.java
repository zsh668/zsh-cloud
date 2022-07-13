package com.zsh.cloud.system.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.tenant.entity.TenantBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 岗位DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/26 11:44
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_station")
public class SysStationDO extends TenantBaseDO {
    
    /**
     * 岗位编码.
     */
    private String stationCode;
    
    /**
     * 名称.
     */
    private String stationName;
    
    /**
     * 组织ID.
     */
    private String orgId;
    
    /**
     * 排序.
     */
    private Integer sortValue;
    
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
