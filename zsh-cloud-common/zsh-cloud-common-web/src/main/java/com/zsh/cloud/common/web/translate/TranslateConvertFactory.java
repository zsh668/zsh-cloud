package com.zsh.cloud.common.web.translate;

import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 转换父类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/13 17:21
 */
@Component
public class TranslateConvertFactory {
    
    private final Map<Class<?>, TranslateTypeConvert> map = new ConcurrentHashMap<>();
    
    /**
     * 增加转换策略.
     *
     * @param translateTypeConvert
     * @param <T>
     */
    public <T> void addConvert(TranslateTypeConvert<T> translateTypeConvert) {
        Type genericInterface = translateTypeConvert.getClass().getGenericInterfaces()[0];
        ParameterizedType parameterizedType = (ParameterizedType) genericInterface;
        Type actualTypeArgument = parameterizedType.getActualTypeArguments()[0];
        map.put((Class<?>) actualTypeArgument, translateTypeConvert);
    }
    
    public <T> TranslateTypeConvert<T> getTranslateTypeConvert(Class<T> convertClass) {
        return map.get(convertClass);
    }
}
