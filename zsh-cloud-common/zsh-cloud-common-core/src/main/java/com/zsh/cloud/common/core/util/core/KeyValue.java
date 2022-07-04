package com.zsh.cloud.common.core.util.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Key Value 的键值对.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/4 09:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyValue<K, V> {
    
    /**
     * key.
     */
    private K key;
    
    /**
     * value.
     */
    private V value;
    
}
