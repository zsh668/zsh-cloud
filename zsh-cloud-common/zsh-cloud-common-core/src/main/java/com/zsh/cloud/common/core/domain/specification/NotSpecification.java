package com.zsh.cloud.common.core.domain.specification;

/**
 * @author hang
 * @version 1.0
 * @date 2022/03/16 11:26
 */
public class NotSpecification<T> extends AbstractSpecification<T> {
    
    private Specification<T> spec1;
    
    public NotSpecification(final Specification<T> spec1) {
        this.spec1 = spec1;
    }
    
    @Override
    public boolean isSatisfiedBy(T t) {
        return !spec1.isSatisfiedBy(t);
    }
}
