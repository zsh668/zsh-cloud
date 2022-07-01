package com.zsh.cloud.common.core.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/3/23 11:54
 */
@ApiModel("分页")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> implements Serializable {
    
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", required = true)
    protected Long current = 1L;
    
    /**
     * 每页显示条数，默认 10
     */
    @ApiModelProperty(value = "每页数", required = true)
    protected Long size = 10L;
    
    /**
     * 总数.
     */
    @ApiModelProperty(value = "总条数", required = true)
    private Long total;
    
    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数", required = true)
    private Long pages;
    
    /**
     * 数据.
     */
    @ApiModelProperty(value = "数据", required = true)
    private List<T> list;
}
