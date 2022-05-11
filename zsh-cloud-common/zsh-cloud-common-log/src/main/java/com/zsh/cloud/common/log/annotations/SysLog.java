package com.zsh.cloud.common.log.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 系统日志注解.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/9 14:16
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    
    /**
     * 描述.
     *
     * @return
     */
    String value() default "";
}
