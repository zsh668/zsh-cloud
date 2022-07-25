package com.zsh.cloud.common.core.util;

import cn.hutool.core.collection.CollUtil;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 参数校验工具类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 11:05
 */
public class ValidatorUtils {
    
    private static Validator validator;
    
    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
    
    /**
     * 校验对象，抛异常.
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     */
    public static void validateEntityThrow(Object object, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (CollUtil.isNotEmpty(constraintViolations)) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
    
    /**
     * 校验对象.
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     * @return 错误信息
     */
    public static String validateEntity(Object object, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        return constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
    }
}
