package com.zsh.cloud.common.web.excel.export.csv;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表头.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2023/12/29 15:00
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelName {
    
    /**
     * 名称.
     *
     * @return
     */
    String name() default "";
}
