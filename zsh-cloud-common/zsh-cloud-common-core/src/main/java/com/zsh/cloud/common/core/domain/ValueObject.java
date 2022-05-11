package com.zsh.cloud.common.core.domain;

import java.io.Serializable;

/**
 * 值比较
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/16 10:05
 */
public interface ValueObject<T> extends Serializable {
    
    boolean sameValueAs(T other);
}
