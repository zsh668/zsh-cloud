package com.zsh.cloud.common.web.translate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 增加该注解，表示此字段内部需要进行翻译.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/13 17:21
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.FIELD)
public @interface TranslateField {

}
