package com.zsh.cloud.common.influxdb.core.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * 基类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2024/3/25 14:02
 */
@Data
@NoArgsConstructor
public class BaseModel {
    
    /**
     * 表
     */
    private String measurement;
    
    /**
     * 条件
     */
    private String where;
    
    /**
     * 开始时间
     */
    private LocalDateTime start;
    
    /**
     * 结束时间
     */
    private LocalDateTime end;
    
    /**
     * where 条件额外参数
     */
    private Map<String, Object> map;
    
    public BaseModel(String measurement) {
        this.measurement = measurement;
    }
}
