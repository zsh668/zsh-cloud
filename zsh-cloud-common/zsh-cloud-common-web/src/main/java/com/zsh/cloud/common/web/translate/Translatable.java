package com.zsh.cloud.common.web.translate;

/**
 * 转换接口.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/13 17:21
 */
public interface Translatable<T> {
    
    /**
     * 翻译
     *
     * @param original
     * @param datasource
     * @param param
     * @return
     */
    String translate(T original, Class<?> datasource, String param);
}
