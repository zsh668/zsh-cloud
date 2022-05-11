package com.zsh.cloud.common.core.domain.specification;

/**
 * @author hang
 * @version 1.0
 * @date 2022/03/16 11:21
 */
public interface Specification<T> {
    
    boolean isSatisfiedBy(T t);
    
    Specification<T> and(Specification<T> specification);
    
    Specification<T> or(Specification<T> specification);
    
    Specification<T> not(Specification<T> specification);
}
