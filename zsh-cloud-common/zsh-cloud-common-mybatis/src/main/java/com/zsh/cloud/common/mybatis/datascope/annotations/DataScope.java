package com.zsh.cloud.common.mybatis.datascope.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据权限注解.（必须在xml中写SQL）
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/12 18:42
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface DataScope {
    
    /**
     * 别名.
     *
     * @return
     */
    String alias() default "";
    
    /**
     * 是否忽略.
     *
     * @return
     */
    boolean ignore() default false;
}