package com.zsh.cloud.common.influxdb.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * influxdb 分页查询 count 字段注解.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2024/3/25 14:12
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Count {
    
    /**
     * 字段名
     *
     * @return
     */
    String value() default "";
}
