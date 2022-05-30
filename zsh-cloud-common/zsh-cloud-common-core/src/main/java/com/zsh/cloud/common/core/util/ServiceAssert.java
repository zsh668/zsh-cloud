package com.zsh.cloud.common.core.util;

import com.zsh.cloud.common.core.exception.ServiceException;

import java.util.Collection;
import java.util.Objects;

/**
 * 业务逻辑断言.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/25 15:16
 */
public class ServiceAssert {
    
    /**
     * 非true抛异常.
     *
     * @param expression
     * @param code
     * @param message
     */
    public static void isTrue(boolean expression, int code, String message) {
        if (!expression) {
            throw new ServiceException(code, message);
        }
    }
    
    /**
     * true 抛异常.
     *
     * @param expression
     * @param code
     * @param message
     */
    public static void notTrue(boolean expression, int code, String message) {
        if (expression) {
            throw new ServiceException(code, message);
        }
    }
    
    /**
     * 非null抛异常.
     *
     * @param object
     * @param code
     * @param message
     */
    public static void isNull(Object object, int code, String message) {
        if (!Objects.isNull(object)) {
            throw new ServiceException(code, message);
        }
    }
    
    /**
     * null抛异常.
     *
     * @param object
     * @param code
     * @param message
     */
    public static void notNull(Object object, int code, String message) {
        if (Objects.isNull(object)) {
            throw new ServiceException(code, message);
        }
    }
    
    /**
     * 非Empty抛异常.
     *
     * @param collection
     * @param code
     * @param message
     */
    public static void isEmpty(Collection<?> collection, int code, String message) {
        if (!Objects.isNull(collection) && !collection.isEmpty()) {
            throw new ServiceException(code, message);
        }
    }
    
    /**
     * Empty抛异常.
     *
     * @param collection
     * @param code
     * @param message
     */
    public static void notEmpty(Collection<?> collection, int code, String message) {
        if (Objects.isNull(collection) || collection.isEmpty()) {
            throw new ServiceException(code, message);
        }
    }
}
