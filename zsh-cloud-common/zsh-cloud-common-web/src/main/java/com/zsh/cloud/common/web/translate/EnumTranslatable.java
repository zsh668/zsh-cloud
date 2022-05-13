package com.zsh.cloud.common.web.translate;

import com.zsh.cloud.common.core.domain.IDict;

import java.util.stream.Stream;

/**
 * 字典转换.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/13 17:21
 */
public class EnumTranslatable implements Translatable<Object> {
    
    @Override
    @SuppressWarnings("unchecked")
    public String translate(Object original, Class<?> datasource, String param) {
        Class<IDict<?>> iDict = (Class<IDict<?>>) datasource;
        return Stream.of(iDict.getEnumConstants()).filter(i -> i.getCode().equals(original)).map(IDict::getText)
                .findFirst().orElse(null);
    }
}
