package com.zsh.cloud.common.core.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
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
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> implements Serializable {
    
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
    
    /**
     * 空.
     *
     * @param <T> 实体
     * @return
     */
    public static <T> Page<T> empty() {
        return new Page<>(0L, 0L, new ArrayList<T>());
    }
}
