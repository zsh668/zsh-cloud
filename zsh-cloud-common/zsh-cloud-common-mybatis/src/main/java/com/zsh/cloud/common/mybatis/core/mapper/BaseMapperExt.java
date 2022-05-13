package com.zsh.cloud.common.mybatis.core.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.core.dto.PageQuery;
import com.zsh.cloud.common.mybatis.core.query.LbqwExt;
import com.zsh.cloud.common.mybatis.core.query.QwExt;
import com.zsh.cloud.common.mybatis.datascope.annotations.DataScope;
import com.zsh.cloud.common.mybatis.util.MyBatisUtils;
import com.zsh.cloud.common.mybatis.util.PageAssembler;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * 在 MyBatis Plus 的 BaseMapper 的基础上拓展，提供更多的能力.
 *
 * @param <T> 实体
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/3/23 11:54
 */
public interface BaseMapperExt<T> extends BaseMapper<T> {
    
    /**
     * 根据单一条件查询.
     *
     * @param field 数据库字段
     * @param value 值
     * @return T
     */
    default T selectOne(String field, Object value) {
        return selectOne(new QwExt<T>().eq(field, value));
    }
    
    /**
     * 根据单一条件查询.
     *
     * @param field 实体字段
     * @param value 值
     * @return T
     */
    default T selectOne(SFunction<T, ?> field, Object value) {
        return selectOne(new LbqwExt<T>().eq(field, value));
    }
    
    /**
     * 根据2个条件查询.
     *
     * @param field1 数据库字段1
     * @param value1 值
     * @param field2 数据库字段2
     * @param value2 值
     * @return T
     */
    default T selectOne(String field1, Object value1, String field2, Object value2) {
        return selectOne(new QwExt<T>().eq(field1, value1).eq(field2, value2));
    }
    
    /**
     * 根据2个条件查询.
     *
     * @param field1 实体字段1
     * @param value1 值
     * @param field2 实体字段2
     * @param value2 值
     * @return T
     */
    default T selectOne(SFunction<T, ?> field1, Object value1, SFunction<T, ?> field2, Object value2) {
        return selectOne(new LbqwExt<T>().eq(field1, value1).eq(field2, value2));
    }
    
    /**
     * 获取总条数.
     *
     * @return Long
     */
    default Long selectCount() {
        return selectCount(new QwExt<T>());
    }
    
    /**
     * 根据单一条件获取总数.
     *
     * @param field 数据库字段
     * @param value 值
     * @return Long
     */
    default Long selectCount(String field, Object value) {
        return selectCount(new QwExt<T>().eq(field, value));
    }
    
    /**
     * 根据单一条件获取总数.
     *
     * @param field 实体字段
     * @param value 值
     * @return Long
     */
    default Long selectCount(SFunction<T, ?> field, Object value) {
        return selectCount(new LbqwExt<T>().eq(field, value));
    }
    
    /**
     * 获取所有数据.
     *
     * @return list
     */
    default List<T> selectList() {
        return selectList(new QwExt<>());
    }
    
    /**
     * 根据单一条件查询数据.
     *
     * @param field 数据库字段
     * @param value 值
     * @return list
     */
    default List<T> selectList(String field, Object value) {
        return selectList(new QwExt<T>().eq(field, value));
    }
    
    /**
     * 根据单一条件查询数据.
     *
     * @param field 实体字段
     * @param value 值
     * @return list
     */
    default List<T> selectList(SFunction<T, ?> field, Object value) {
        return selectList(new LbqwExt<T>().eq(field, value));
    }
    
    /**
     * 根据单一条件查询数据.
     *
     * @param field  数据库字段
     * @param values 值集合
     * @return list
     */
    default List<T> selectList(String field, Collection<?> values) {
        return selectList(new QwExt<T>().in(field, values));
    }
    
    /**
     * 根据单一条件查询数据.
     *
     * @param field  实体字段
     * @param values 值集合
     * @return list
     */
    default List<T> selectList(SFunction<T, ?> field, Collection<?> values) {
        return selectList(new LbqwExt<T>().in(field, values));
    }
    
    /**
     * 根据查询条件分页查询数据.
     *
     * @param pageQuery    分页参数
     * @param queryWrapper 查询条件
     * @return
     */
    default Page<T> selectPage(PageQuery pageQuery, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        // MyBatis Plus 查询
        IPage<T> iPage = MyBatisUtils.buildPage(pageQuery);
        selectPage(iPage, queryWrapper);
        // 转换返回
        return PageAssembler.toPage(iPage);
    }
}
