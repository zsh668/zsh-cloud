package com.zsh.cloud.common.web.repeat;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 重复请求拦截注解.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/13 17:19
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface RepeatRequestIntercept {

}
