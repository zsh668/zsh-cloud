package com.zsh.cloud.common.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基类.
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/11 18:09
 */
@Data
public class BaseDO implements Serializable {
    
    /**
     * id
     */
    @TableId
    private String id;
    
    /**
     * 删除标识
     */
    private Integer delFlag;
    
    /**
     * 创建人
     */
    private String createdBy;
    
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
    
    /**
     * 更新人
     */
    private String updatedBy;
    
    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;
}
