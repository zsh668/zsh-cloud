package com.zsh.cloud.common.core.cache;

import org.springframework.context.SmartLifecycle;

/**
 * 本地缓存.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/13 11:12
 */

public interface LocalCache extends SmartLifecycle {
    
    /**
     * 初始化缓存
     */
    void initCache();
    
    @Override
    default void start() {
        initCache();
    }
    
    @Override
    default void stop() {
    }
    
    @Override
    default boolean isRunning() {
        return false;
    }
}
