package com.zsh.cloud.common.influxdb.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 常用的聚合函数枚举.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2024/3/25 14:10
 */
@Getter
@AllArgsConstructor
public enum Function {
    
    SUM("sum", "累加"), LAST("last", "最后一条数据"), MEAN("mean", "平均数");
    
    private final String tag;
    
    private final String content;
}
