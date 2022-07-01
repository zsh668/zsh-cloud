package com.zsh.cloud.system.infrastructure.persistence.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsh.cloud.common.mybatis.core.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 资源DO.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 09:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_resource")
public class SysResourceDO extends BaseDO {
    
    /**
     * 接口名称
     */
    private String resourceName;
    
    /**
     * 资源编码 规则： 链接： 数据列： 按钮：
     */
    private String resourceCode;
    
    /**
     * 菜单ID
     */
    private String menuId;
    
    /**
     * 请求方式
     */
    private String method;
    
    /**
     * 请求地址
     */
    private String url;
    
    /**
     * 状态 1启用，0禁用.
     */
    private Boolean status;
    
    /**
     * 接口描述
     */
    @TableField(value = "describe_", condition = SqlCondition.LIKE)
    private String describe;
}
