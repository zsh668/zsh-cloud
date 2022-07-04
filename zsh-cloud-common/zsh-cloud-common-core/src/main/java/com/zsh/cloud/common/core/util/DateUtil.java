package com.zsh.cloud.common.core.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 时间工具类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/19 16:51
 */
@UtilityClass
@Slf4j
public class DateUtil {
    
    public static final String FULL_TIME_PATTERN = "yyyyMMddHHmmss";
    
    public static final String FULL_TIME_SPLIT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    
    /**
     * 格式化日期, 返回格式为 yyyy-MM-dd
     *
     * @param date 日期
     * @return
     */
    public static String formatAsDate(LocalDateTime date) {
        return format(date, DEFAULT_DATE_FORMAT);
    }
    
    /**
     * 格式化日期, 默认返回格式为 yyyy-MM-dd HH:mm:ss.
     *
     * @param date 日期
     * @return
     */
    public static String format(LocalDateTime date, String pattern) {
        if (date == null) {
            date = LocalDateTime.now();
        }
        if (pattern == null) {
            pattern = FULL_TIME_SPLIT_PATTERN;
        }
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }
    
    /**
     * 获取当前时间戳.
     *
     * @return
     */
    public static Long nowMillis() {
        return nowMillis(LocalDateTime.now());
    }
    
    /**
     * 获取时间戳.
     *
     * @return
     */
    public static Long nowMillis(LocalDateTime dateTime) {
        return dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }
}
