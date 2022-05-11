package com.zsh.cloud.common.core.domain;

import java.io.Serializable;

/**
 * 实体id比较
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/16 10:13
 */
public interface Entity<T> extends Serializable {
    
    boolean sameIdentityAs(T other);
}
