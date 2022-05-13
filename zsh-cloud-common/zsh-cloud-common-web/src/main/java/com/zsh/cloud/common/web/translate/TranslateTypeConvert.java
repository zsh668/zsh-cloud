package com.zsh.cloud.common.web.translate;

/**
 * 自定义函数.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/13 17:21
 */
@FunctionalInterface
public interface TranslateTypeConvert<T> {
    
    Object convert(T object);
}
