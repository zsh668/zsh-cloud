package com.zsh.cloud.common.mybatis.conditions.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * 简化代码长度 并 拓展 MyBatis Plus QueryWrapper 类，主要增加如下功能：
 * <p></p>
 * 1. 拼接条件的方法，增加 xxxIfPresent 方法，用于判断值不存在的时候，不要拼接到条件中。
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/3/23 15:36
 */
public class QwExt<T> extends QueryWrapper<T> {
    
    /**
     * like 字段值是否为空
     *
     * @param column 字段
     * @param val    值
     * @return this
     */
    public QwExt<T> likeIfPresent(String column, String val) {
        if (StringUtils.hasText(val)) {
            return (QwExt<T>) super.like(column, val);
        }
        return this;
    }
    
    /**
     * in
     *
     * @param column 字段
     * @param values 值集合
     * @return
     */
    public QwExt<T> inIfPresent(String column, Collection<?> values) {
        if (!CollectionUtils.isEmpty(values)) {
            return (QwExt<T>) super.in(column, values);
        }
        return this;
    }
    
    /**
     * in
     *
     * @param column 字段
     * @param values 值数组
     * @return
     */
    public QwExt<T> inIfPresent(String column, Object... values) {
        if (!ArrayUtils.isEmpty(values)) {
            return (QwExt<T>) super.in(column, values);
        }
        return this;
    }
    
    /**
     * 等于 =
     *
     * @param column 字段
     * @param val    值
     * @return
     */
    public QwExt<T> eqIfPresent(String column, Object val) {
        if (val != null) {
            return (QwExt<T>) super.eq(column, val);
        }
        return this;
    }
    
    /**
     * 不等于 !=
     *
     * @param column 字段
     * @param val    值
     * @return
     */
    public QwExt<T> neIfPresent(String column, Object val) {
        if (val != null) {
            return (QwExt<T>) super.ne(column, val);
        }
        return this;
    }
    
    /**
     * 大于 >
     *
     * @param column 字段
     * @param val    值
     * @return
     */
    public QwExt<T> gtIfPresent(String column, Object val) {
        if (val != null) {
            return (QwExt<T>) super.gt(column, val);
        }
        return this;
    }
    
    /**
     * 大于等于 >=
     *
     * @param column 字段
     * @param val    值
     * @return
     */
    public QwExt<T> geIfPresent(String column, Object val) {
        if (val != null) {
            return (QwExt<T>) super.ge(column, val);
        }
        return this;
    }
    
    /**
     * 小于 <
     *
     * @param column 字段
     * @param val    值
     * @return
     */
    public QwExt<T> ltIfPresent(String column, Object val) {
        if (val != null) {
            return (QwExt<T>) super.lt(column, val);
        }
        return this;
    }
    
    /**
     * 小于等于 <=
     *
     * @param column 字段
     * @param val    值
     * @return
     */
    public QwExt<T> leIfPresent(String column, Object val) {
        if (val != null) {
            return (QwExt<T>) super.le(column, val);
        }
        return this;
    }
    
    /**
     * BETWEEN 值1 AND 值2
     *
     * @param column 字段
     * @param val1   值1
     * @param val2   值2
     * @return
     */
    public QwExt<T> betweenIfPresent(String column, Object val1, Object val2) {
        if (val1 != null && val2 != null) {
            return (QwExt<T>) super.between(column, val1, val2);
        }
        if (val1 != null) {
            return (QwExt<T>) ge(column, val1);
        }
        if (val2 != null) {
            return (QwExt<T>) le(column, val2);
        }
        return this;
    }
    
    // ========== 重写父类方法，方便链式调用 ==========
    @Override
    public QwExt<T> eq(boolean condition, String column, Object val) {
        super.eq(condition, column, val);
        return this;
    }
    
    @Override
    public QwExt<T> eq(String column, Object val) {
        super.eq(column, val);
        return this;
    }
    
    @Override
    public QwExt<T> orderByDesc(String column) {
        super.orderByDesc(true, column);
        return this;
    }
    
    @Override
    public QwExt<T> last(String lastSql) {
        super.last(lastSql);
        return this;
    }
    
    @Override
    public QwExt<T> in(String column, Collection<?> coll) {
        super.in(column, coll);
        return this;
    }
}
