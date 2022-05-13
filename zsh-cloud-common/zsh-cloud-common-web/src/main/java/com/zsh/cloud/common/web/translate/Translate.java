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
    
    Class<? extends Translatable> translator() default Translatable.class;
    
    Class<?> dataSource() default Void.class;
    
    String from();
    
    String param() default "";
    
}
