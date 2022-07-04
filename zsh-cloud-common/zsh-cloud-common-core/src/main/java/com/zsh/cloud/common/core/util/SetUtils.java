package com.zsh.cloud.common.core.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Set 工具类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/4 09:37
 */
public class SetUtils {
    
    public static <T> Set<T> asSet(T... objs) {
        return new HashSet<>(Arrays.asList(objs));
    }
    
}
