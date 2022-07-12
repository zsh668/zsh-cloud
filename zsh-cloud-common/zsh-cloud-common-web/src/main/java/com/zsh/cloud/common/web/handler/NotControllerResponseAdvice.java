package com.zsh.cloud.common.web.handler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 无需返回值包装.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/11 11:04
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotControllerResponseAdvice {

}
