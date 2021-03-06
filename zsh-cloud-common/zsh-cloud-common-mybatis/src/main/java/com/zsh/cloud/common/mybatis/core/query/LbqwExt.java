package com.zsh.cloud.common.mybatis.core.query;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import lombok.val;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

/**
 * 简化代码长度 并 拓展 MyBatis Plus QueryWrapper 类，主要增加如下功能：
 * <p></p>
 * 1. 拼接条件的方法，增加 xxxIfPresent 方法，用于判断值不存在的时候，不要拼接到条件中。
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/3/23 15:17
 */
public class LbqwExt<T> extends LambdaQueryWrapper<T> {
    
    /**
     * like 字段值是否为空
     *
     * @param column 字段
     * @param val    值
     * @return this
     */
    public LbqwExt<T> likeIfPresent(SFunction<T, ?> column, String val) {
        if (StringUtils.hasText(val)) {
            return (LbqwExt<T>) super.like(column, val);
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
    public LbqwExt<T> inIfPresent(SFunction<T, ?> column, Collection<?> values) {
        if (!CollectionUtils.isEmpty(values)) {
            return (LbqwExt<T>) super.in(column, values);
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
    public LbqwExt<T> inIfPresent(SFunction<T, ?> column, Object... values) {
        if (!ArrayUtils.isEmpty(values)) {
            return (LbqwExt<T>) super.in(column, values);
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
    public LbqwExt<T> eqIfPresent(SFunction<T, ?> column, Object val) {
        if (!ObjectUtils.isEmpty(val)) {
            return (LbqwExt<T>) super.eq(column, val);
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
    public LbqwExt<T> neIfPresent(SFunction<T, ?> column, Object val) {
        if (!ObjectUtils.isEmpty(val)) {
            return (LbqwExt<T>) super.ne(column, val);
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
    public LbqwExt<T> gtIfPresent(SFunction<T, ?> column, Object val) {
        if (!ObjectUtils.isEmpty(val)) {
            return (LbqwExt<T>) super.gt(column, val);
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
    public LbqwExt<T> geIfPresent(SFunction<T, ?> column, Object val) {
        if (!ObjectUtils.isEmpty(val)) {
            return (LbqwExt<T>) super.ge(column, val);
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
    public LbqwExt<T> ltIfPresent(SFunction<T, ?> column, Object val) {
        if (!ObjectUtils.isEmpty(val)) {
            return (LbqwExt<T>) super.lt(column, val);
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
    public LbqwExt<T> leIfPresent(SFunction<T, ?> column, Object val) {
        if (!ObjectUtils.isEmpty(val)) {
            return (LbqwExt<T>) super.le(column, val);
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
    public LbqwExt<T> betweenIfPresent(SFunction<T, ?> column, Object val1, Object val2) {
        if (!ObjectUtils.isEmpty(val1) && !ObjectUtils.isEmpty(val2)) {
            return (LbqwExt<T>) super.between(column, val1, val2);
        }
        if (!ObjectUtils.isEmpty(val1)) {
            return (LbqwExt<T>) ge(column, val1);
        }
        if (!ObjectUtils.isEmpty(val2)) {
            return (LbqwExt<T>) le(column, val2);
        }
        return this;
    }
    
    /**
     * 大于等于 >=（时间）
     *
     * @param column
     * @param val    时间
     * @return
     */
    public LbqwExt<T> geHeaderIfPresent(SFunction<T, ?> column, LocalDateTime val) {
        if (ObjectUtils.isEmpty(val)) {
            return this;
        }
        if (val != null) {
            val = LocalDateTime.of(val.toLocalDate(), LocalTime.MIN);
        }
        return (LbqwExt<T>) ge(column, val);
    }
    
    /**
     * 大于等于 >=（时间）
     *
     * @param column
     * @param val    日期
     * @return
     */
    public LbqwExt<T> geHeaderIfPresent(SFunction<T, ?> column, LocalDate val) {
        if (ObjectUtils.isEmpty(val)) {
            return this;
        }
        LocalDateTime dateTime = LocalDateTime.of(val, LocalTime.MIN);
        return (LbqwExt<T>) ge(column, val);
    }
    
    /**
     * 小于等于 <=（时间）
     *
     * @param column
     * @param val    时间
     * @return
     */
    public LbqwExt<T> leFooterIfPresent(SFunction<T, ?> column, LocalDateTime val) {
        if (ObjectUtils.isEmpty(val)) {
            return this;
        }
        val = LocalDateTime.of(val.toLocalDate(), LocalTime.MAX);
        return (LbqwExt<T>) le(column, val);
    }
    
    /**
     * 小于等于 <=（时间）
     *
     * @param column
     * @param val    日期
     * @return
     */
    public LbqwExt<T> leFooterIfPresent(SFunction<T, ?> column, LocalDate val) {
        if (ObjectUtils.isEmpty(val)) {
            return this;
        }
        LocalDateTime dateTime = LocalDateTime.of(val, LocalTime.MAX);
        return (LbqwExt<T>) le(column, dateTime);
    }
    
    // ========== 重写父类方法，方便链式调用 ==========
    @Override
    public LbqwExt<T> eq(boolean condition, SFunction<T, ?> column, Object val) {
        super.eq(condition, column, val);
        return this;
    }
    
    @Override
    public LbqwExt<T> eq(SFunction<T, ?> column, Object val) {
        super.eq(column, val);
        return this;
    }
    
    @Override
    public LbqwExt<T> orderByDesc(SFunction<T, ?> column) {
        super.orderByDesc(true, column);
        return this;
    }
    
    @Override
    public LbqwExt<T> last(String lastSql) {
        super.last(lastSql);
        return this;
    }
    
    @Override
    public LbqwExt<T> in(SFunction<T, ?> column, Collection<?> coll) {
        super.in(column, coll);
        return this;
    }
}
