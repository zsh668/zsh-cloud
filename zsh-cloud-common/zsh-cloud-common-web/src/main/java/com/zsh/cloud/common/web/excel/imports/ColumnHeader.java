package com.zsh.cloud.common.web.excel.imports;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标题字段.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/19 15:47
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnHeader {
    
    /**
     * 标题
     **/
    String title() default "";
    
    /**
     * 描述
     **/
    String desc() default "";
    
    /**
     * 是否必填
     **/
    boolean notNull() default true;
    
    /**
     * 示例值
     **/
    String example() default "";
}
