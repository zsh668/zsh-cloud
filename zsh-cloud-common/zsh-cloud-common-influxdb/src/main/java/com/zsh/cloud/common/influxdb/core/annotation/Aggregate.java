package com.zsh.cloud.common.influxdb.core.annotation;

import com.zsh.cloud.common.influxdb.core.enums.Function;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 常用聚合函数字段注解
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2024/3/25 14:11
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Aggregate {
    
    /**
     * 字段名
     *
     * @return
     */
    String value();
    
    /**
     * 字段使用的聚合函数
     *
     * @return
     */
    Function tag();
}
