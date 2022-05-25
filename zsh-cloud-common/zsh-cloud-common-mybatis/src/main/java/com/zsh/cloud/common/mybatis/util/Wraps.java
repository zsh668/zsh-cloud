package com.zsh.cloud.common.mybatis.util;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zsh.cloud.common.mybatis.core.query.LbqwExt;
import com.zsh.cloud.common.mybatis.core.query.QwExt;

/**
 * 简化MyBatis plus工具类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/25 18:22
 */
public class Wraps {
    
    public static <T> QwExt<T> qw() {
        return new QwExt();
    }
    
    public static <T> QwExt<T> qw(T entity) {
        return (QwExt<T>) new QueryWrapper(entity);
    }
    
    public static <T> LbqwExt<T> lbQ() {
        return new LbqwExt();
    }
    
    public static <T> LbqwExt<T> lbQ(T entity) {
        return (LbqwExt<T>) new LambdaQueryWrapper(entity);
    }
    
    public static <T> UpdateWrapper<T> uw() {
        return new UpdateWrapper();
    }
    
    public static <T> UpdateWrapper<T> uw(T entity) {
        return new UpdateWrapper(entity);
    }
    
    public static <T> LambdaUpdateWrapper<T> lbU() {
        return new LambdaUpdateWrapper();
    }
    
    public static <T> LambdaUpdateWrapper<T> lbU(T entity) {
        return new LambdaUpdateWrapper(entity);
    }
}
