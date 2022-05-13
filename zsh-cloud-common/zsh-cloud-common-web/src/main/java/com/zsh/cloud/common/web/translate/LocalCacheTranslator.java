package com.zsh.cloud.common.web.translate;

import com.zsh.cloud.common.core.cache.AbstractLocalCache;
import com.zsh.cloud.common.core.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * 本地缓存 转换策略.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/13 17:21
 */
@Component
@Slf4j
public class LocalCacheTranslator<K, V> implements Translatable<K> {
    
    @SuppressWarnings("unchecked")
    @Override
    public String translate(K original, Class<?> datasource, String param) {
        if (AbstractLocalCache.class.isAssignableFrom(datasource)) {
            AbstractLocalCache<K, V> abstractLocalCache = (AbstractLocalCache<K, V>) SpringUtils.getBean(datasource);
            V cache = abstractLocalCache.getCache(original);
            Field field;
            try {
                if (cache == null) {
                    log.error("翻译无内容 cacheId {} datasource {}", original, datasource);
                    return null;
                }
                field = cache.getClass().getDeclaredField(param);
            } catch (NoSuchFieldException e) {
                log.error("找不到字段 {}", param, e);
                return null;
            }
            try {
                field.setAccessible(true);
                Object o = field.get(cache);
                Optional<Object> optional = Optional.ofNullable(o);
                return optional.map(Object::toString).orElse(null);
            } catch (IllegalAccessException e) {
                log.error("获取 class {} 字段 {} 失败", cache.getClass(), param);
                return null;
            }
        }
        return null;
    }
}
