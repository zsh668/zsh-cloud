package com.zsh.cloud.common.core.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 本地缓存工具抽象类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/13 11:12
 */
@EnableScheduling
@Slf4j
public abstract class AbstractLocalCache<K, V> implements LocalCache, SchedulingConfigurer {
    
    private Long lastUpdateTime;
    
    protected final Map<K, V> cache = new ConcurrentHashMap<>(40000);
    
    /**
     * 初始化缓存
     */
    @Override
    public void initCache() {
        lastUpdateTime = System.currentTimeMillis();
        boolean result = allCacheData();
        if (!result) {
            lastUpdateTime = 0L;
        }
    }
    
    /**
     * 增量数据的延迟间隔时间
     *
     * @return
     */
    protected abstract long incrementCacheCron();
    
    /**
     * 全量数据加载的延迟间隔时间
     *
     * @return
     */
    protected abstract CronTrigger allCacheCron();
    
    /**
     * 增量数据
     *
     * @param lastUpdateTime
     */
    protected abstract void incrementCacheData(Long lastUpdateTime);
    
    private void incrementCacheData() {
        Long lastUpdate = lastUpdateTime;
        lastUpdateTime = System.currentTimeMillis();
        incrementCacheData(lastUpdate);
    }
    
    /**
     * 全量数据
     */
    protected abstract boolean allCacheData();
    
    /**
     * 制定cache key
     *
     * @return
     */
    protected abstract Function<V, K> cacheKey();
    
    /**
     * 如果cache没有命中的处理方法
     *
     * @param cacheId
     * @return
     */
    protected abstract V ifNull(K cacheId);
    
    public V getById(K cacheId) {
        return getCache(cacheId);
    }
    
    @Nullable
    public V getCache(K cacheId) {
        if (cacheId == null) {
            log.warn("UserCache查询用户缓存时传入的cacheId为null，请确认是否传参正确");
            return null;
        }
        return Optional.ofNullable(cache.get(cacheId)).orElse(ifNull(cacheId));
    }
    
    public void ifPresent(K cacheId, Consumer<? super V> consumer) {
        Optional.ofNullable(getCache(cacheId)).ifPresent(consumer);
    }
    
    public <T> T getCacheProperty(K userId, Function<V, T> mapper) {
        return Optional.ofNullable(getCache(userId)).map(mapper).orElse(null);
    }
    
    public void putCache(K k, V v) {
        cache.put(k, v);
    }
    
    /**
     * 增加缓存.
     *
     * @param cacheData
     */
    public void pushCache(List<V> cacheData) {
        lastUpdateTime = System.currentTimeMillis();
        cacheData.forEach(v -> {
            Optional<K> k = Optional.ofNullable(v).map(cacheKey());
            k.ifPresent(k1 -> putCache(k1, v));
        });
    }
    
    public List<K> findIdsBy(Predicate<? super V> predicate) {
        return cache.values().stream().filter(predicate).map(cacheKey()).collect(Collectors.toList());
    }
    
    public Map<K, V> findMapBy(Predicate<? super V> predicate) {
        return cache.values().stream().filter(predicate).collect(Collectors.toMap(cacheKey(), Function.identity()));
    }
    
    public List<V> findBy(Predicate<? super V> predicate) {
        return cache.values().stream().filter(predicate).collect(Collectors.toList());
    }
    
    @Override
    public void configureTasks(@Nullable ScheduledTaskRegistrar scheduledTaskRegistrar) {
        log.info("初始化cache任务");
        if (scheduledTaskRegistrar != null) {
            scheduledTaskRegistrar.addFixedDelayTask(this::incrementCacheData, incrementCacheCron());
            scheduledTaskRegistrar.addTriggerTask(this::allCacheData, allCacheCron());
        }
    }
}
