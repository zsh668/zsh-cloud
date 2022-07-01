package com.zsh.cloud.system.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.mybatis.core.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 09:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_menu")
public class SysMenuDO extends BaseDO {
    
    /**
     * 菜单名称.
     */
    private String menuName;
    
    /**
     * 父级菜单id.
     */
    private String parentId;
    
    /**
     * 是否公开菜单 1是，0否 就是无需分配就可以访问的。所有人可见.
     */
    private Integer isPublic;
    
    /**
     * 对应路由path.
     */
    private String path;
    
    /**
     * 对应路由组件component.
     */
    private String component;
    
    /**
     * 状态 1启用，0禁用.
     */
    private Boolean status;
    
    /**
     * 排序.
     */
    private Integer sortValue;
    
    /**
     * 菜单图标.
     */
    private String icon;
    
    /**
     * 菜单分组.
     */
    @TableField(value = "group_", condition = SqlCondition.LIKE)
    private String group;
    
    /**
     * 功能描述.
     */
    @TableField(value = "describe_", condition = SqlCondition.LIKE)
    private String describe;
}
