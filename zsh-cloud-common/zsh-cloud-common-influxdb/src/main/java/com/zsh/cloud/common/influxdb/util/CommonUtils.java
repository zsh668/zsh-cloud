package com.zsh.cloud.common.influxdb.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2024/3/25 13:54
 */
public class CommonUtils {
    
    private static final Pattern LINE_PATTERN = Pattern.compile("_(\\w)");
    
    private static final Pattern HUMP_PATTERN = Pattern.compile("[A-Z]");
    
    /**
     * 时间转换 Instant字符串时间转 LocalDateTime
     *
     * @param time
     * @return
     */
    public static LocalDateTime parseStringToLocalDateTime(String time) {
        DateTimeFormatter df = DateTimeFormatter.ISO_DATE_TIME;
        return LocalDateTime.parse(time, df);
    }
    
    /**
     * localDateTime 转 Instant
     *
     * @param localDateTime
     * @return
     */
    public static Instant parseLocalDateTimeToInstant(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }
    
    /**
     * 下划线转驼峰
     *
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
        Matcher matcher = LINE_PATTERN.matcher(str.toLowerCase());
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    
    /**
     * 驼峰转下划线,
     *
     * @param str
     * @return
     */
    public static String humpToLine(String str) {
        Matcher matcher = HUMP_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    
}
