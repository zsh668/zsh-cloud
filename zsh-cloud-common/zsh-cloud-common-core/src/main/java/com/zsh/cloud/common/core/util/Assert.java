package com.zsh.cloud.common.core.util;

import com.zsh.cloud.common.core.exception.ServiceException;
import com.zsh.cloud.common.core.exception.code.BaseErrorCode;

import java.util.Collection;
import java.util.Objects;

/**
 * 断言.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/25 15:16
 */
public class Assert {
    
    /**
     * 非true抛异常.
     *
     * @param expression
     * @param errorCode
     */
    public static void isTrue(boolean expression, BaseErrorCode errorCode) {
        if (!expression) {
            throw new ServiceException(errorCode);
        }
    }
    
    /**
     * false抛异常.
     *
     * @param expression
     * @param errorCode
     */
    public static void notTrue(boolean expression, BaseErrorCode errorCode) {
        if (expression) {
            throw new ServiceException(errorCode);
        }
    }
    
    /**
     * 非null抛异常.
     *
     * @param object
     * @param errorCode
     */
    public static void isNull(Object object, BaseErrorCode errorCode) {
        if (!Objects.isNull(object)) {
            throw new ServiceException(errorCode);
        }
    }
    
    /**
     * null抛异常.
     *
     * @param object
     * @param errorCode
     */
    public static void notNull(Object object, BaseErrorCode errorCode) {
        if (Objects.isNull(object)) {
            throw new ServiceException(errorCode);
        }
    }
    
    /**
     * 非Empty抛异常.
     *
     * @param collection
     * @param errorCode
     */
    public static void isEmpty(Collection<?> collection, BaseErrorCode errorCode) {
        if (!Objects.isNull(collection) && !collection.isEmpty()) {
            throw new ServiceException(errorCode);
        }
    }
    
    /**
     * Empty抛异常.
     *
     * @param collection
     * @param errorCode
     */
    public static void notEmpty(Collection<?> collection, BaseErrorCode errorCode) {
        if (Objects.isNull(collection) || collection.isEmpty()) {
            throw new ServiceException(errorCode);
        }
    }
}
