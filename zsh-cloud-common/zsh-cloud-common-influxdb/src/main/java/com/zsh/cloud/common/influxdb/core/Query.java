package com.zsh.cloud.common.influxdb.core;

import com.zsh.cloud.common.influxdb.core.model.QueryModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * 查询.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2024/3/25 13:59
 */
@Slf4j
public class Query extends Op {
    
    /**
     * 构造条件
     *
     * @param model
     * @return
     */
    public static String build(QueryModel model) {
        Objects.requireNonNull(model.getMeasurement(), "QueryModel.Measurement");
        StringBuilder query = new StringBuilder();
        query.append("select ").append(model.getSelect());
        query.append(" from ").append(model.getMeasurement());
        if (!ObjectUtils.isEmpty(model.getWhere())) {
            query.append(" where ").append(model.getWhere());
        }
        if (!ObjectUtils.isEmpty(model.getGroup())) {
            query.append(" group by ").append(model.getGroup());
        }
        if (!ObjectUtils.isEmpty(model.getOrder())) {
            query.append(" order by time ").append(model.getOrder());
        }
        if (!ObjectUtils.isEmpty(model.getCurrent()) && !ObjectUtils.isEmpty(model.getSize())) {
            query.append(" ").append(model.getPageQuery());
        }
        if (model.getUseTimeZone()) {
            query.append(" ").append(model.getTimeZone());
        }
        String sql = query.toString();
        log.info(sql);
        return sql;
    }
    
    /**
     * count Field 字段
     *
     * @param field
     * @return
     */
    public static String count(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("count(").append("\"").append(field).append("\"").append(")");
        return sb.toString();
    }
    
    /**
     * 聚合函数构建
     *
     * @param tag
     * @param field
     * @return
     */
    public static StringBuilder funcAggregate(String tag, String field) {
        StringBuilder sb = new StringBuilder();
        sb.append(tag).append("(").append("\"").append(field).append("\"").append(")");
        sb.append(" as ").append("\"").append(field).append("\"");
        return sb;
    }
    
}
