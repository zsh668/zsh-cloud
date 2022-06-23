package com.zsh.cloud.system.application.query;

import com.zsh.cloud.common.core.dto.PageQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 资源分页Query.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/17 16:37
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("资源分页查询对象")
public class ResourcePageQuery extends PageQuery {
    
    /**
     * 菜单ID.
     */
    private String menuId;
    
    /**
     * 资源编码 规则： 链接： 数据列： 按钮：
     */
    private String resourceCode;
    
    /**
     * 资源名称.
     */
    private String resourceName;
}
