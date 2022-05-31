package com.zsh.cloud.common.web.translate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 翻译注解.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/13 17:21
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.FIELD)
public @interface Translate {
    
    /**
     * 转换策略.
     *
     * @return
     */
    Class<? extends Translatable> translator() default Translatable.class;
    
    /**
     * 取值来源.
     *
     * @return
     */
    Class<?> dataSource() default Void.class;
    
    /**
     * 查询字段.
     *
     * @return
     */
    String from();
    
    /**
     * 取值字段
     *
     * @return
     */
    String param() default "";
    
}
