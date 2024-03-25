package com.zsh.cloud.common.influxdb.core;

import com.zsh.cloud.common.influxdb.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 插入.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2024/3/25 14:00
 */
@Slf4j
public class Insert extends Op {
    
    /**
     * 构造条件
     *
     * @param
     * @return
     */
    public static String build(Object object) {
        Objects.requireNonNull(object, "实体不能为空");
        StringBuilder insert = new StringBuilder();
        String time = "";
        Class<?> clazz = object.getClass();
        Measurement measurement = clazz.getAnnotation(Measurement.class);
        insert.append(measurement.name());
        Field[] fields = clazz.getDeclaredFields();
        int i = 0;
        for (Field field : fields) {
            try {
                Column column = field.getAnnotation(Column.class);
                field.setAccessible(true);
                if (column.tag()) {
                    if (field.get(object) != null) {
                        insert.append(",").append(column.name()).append("=").append(field.get(object));
                    }
                } else {
                    if (field.get(object) != null) {
                        if ("time".equals(column.name())) {
                            time = CommonUtils.parseLocalDateTimeToInstant((LocalDateTime) field.get(object)).getEpochSecond() + "000000000";
                        } else {
                            if (i == 0) {
                                insert.append(" ");
                            } else {
                                insert.append(",");
                            }
                            insert.append(column.name()).append("=").append(field.get(object));
                            i++;
                        }
                        
                    }
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                log.error("Influxdb save error. error :{}", e.getMessage());
            }
        }
        insert.append(" ").append(time);
        String sql = insert.toString();
        log.debug("insert " + sql);
        return sql;
    }
}
