package com.zsh.cloud.common.core.domain.specification;

/**
 * @author hang
 * @version 1.0
 * @date 2022/03/16 11:23
 */
public abstract class AbstractSpecification<T> implements Specification<T> {
    
    public abstract boolean isSatisfiedBy(T t);
    
    public Specification<T> and(Specification<T> specification) {
        return new AndSpecification<T>(this, specification);
    }
    
    public Specification<T> or(Specification<T> specification) {
        return new OrSpecification<T>(this, specification);
    }
    
    public Specification<T> not(Specification<T> specification) {
        return new NotSpecification<T>(specification);
    }
}
