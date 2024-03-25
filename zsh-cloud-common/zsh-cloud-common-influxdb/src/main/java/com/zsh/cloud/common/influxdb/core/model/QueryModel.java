package com.zsh.cloud.common.influxdb.core.model;

import com.zsh.cloud.common.influxdb.core.enums.Order;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

/**
 * 查询.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2024/3/25 14:03
 */
@Data
@NoArgsConstructor
public class QueryModel extends BaseModel {
    
    /**
     * 查询的字段
     */
    private String select;
    
    /**
     * influxdb仅支持time排序
     *
     * Order.DESC Order.ASC
     */
    private Order order;
    
    /**
     * 当前页
     */
    private Long current;
    
    /**
     * 每页的大小
     */
    private Long size;
    
    /**
     * 默认不使用时区
     */
    private Boolean useTimeZone = false;
    
    /**
     * 默认时区 Asia/Shanghai
     */
    private String timeZone = "tz('Asia/Shanghai')";
    
    /**
     * 分组
     */
    private String group;
    
    public QueryModel(String measurement) {
        super(measurement);
    }
    
    public String getPageQuery() {
        return "limit " + size + " offset " + (current - 1) * size;
    }
    
    public String getSelect() {
        if (ObjectUtils.isEmpty(select)) {
            select = "*";
        }
        return select;
    }
}
