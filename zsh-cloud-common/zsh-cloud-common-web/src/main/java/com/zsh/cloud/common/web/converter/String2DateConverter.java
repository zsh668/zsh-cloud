package com.zsh.cloud.common.web.converter;

import com.zsh.cloud.common.core.exception.ServiceException;
import com.zsh.cloud.common.core.exception.code.enums.GlobalErrorCode;
import com.zsh.cloud.common.core.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 解决入参为 Date类型.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/7 14:45
 */
@Slf4j
public class String2DateConverter extends BaseDateConverter<Date> implements Converter<String, Date> {
    
    protected static final Map<String, String> FORMAT = new LinkedHashMap(11);
    
    static {
        FORMAT.put(DateUtil.DEFAULT_YEAR_FORMAT, "^\\d{4}");
        FORMAT.put(DateUtil.DEFAULT_MONTH_FORMAT, "^\\d{4}-\\d{1,2}$");
        FORMAT.put(DateUtil.DEFAULT_DATE_FORMAT, "^\\d{4}-\\d{1,2}-\\d{1,2}$");
        FORMAT.put(DateUtil.DEFAULT_DATE_HOUR_FORMAT, "^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}");
        FORMAT.put(DateUtil.DEFAULT_DATE_HOUR_MINUTE_FORMAT, "^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$");
        FORMAT.put(DateUtil.DEFAULT_DATE_TIME_FORMAT, "^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$");
        FORMAT.put(DateUtil.DEFAULT_MONTH_FORMAT_SLASH, "^\\d{4}/\\d{1,2}$");
        FORMAT.put(DateUtil.DEFAULT_DATE_FORMAT_SLASH, "^\\d{4}/\\d{1,2}/\\d{1,2}$");
        FORMAT.put(DateUtil.DEFAULT_DATE_HOUR_FORMAT_SLASH, "^\\d{4}/\\d{1,2}/\\d{1,2} {1}\\d{1,2}");
        FORMAT.put(DateUtil.DEFAULT_DATE_HOUR_MINUTE_FORMAT_SLASH, "^\\d{4}/\\d{1,2}/\\d{1,2} {1}\\d{1,2}:\\d{1,2}$");
        FORMAT.put(DateUtil.DEFAULT_DATE_TIME_FORMAT_SLASH, "^\\d{4}/\\d{1,2}/\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$");
    }
    
    /**
     * 格式化日期
     *
     * @param dateStr String 字符型日期
     * @param format  String 格式
     * @return Date 日期
     */
    protected static Date parseDate(String dateStr, String format) {
        Date date = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            //严格模式
            dateFormat.setLenient(false);
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            log.info("转换日期失败, date={}, format={}", dateStr, format, e);
            throw new ServiceException(GlobalErrorCode.BAD_REQUEST.getCode(), e.getMessage());
        }
        return date;
    }
    
    @Override
    protected Map<String, String> getFormat() {
        return FORMAT;
    }
    
    @Override
    public Date convert(String source) {
        return super.convert(source, (key) -> parseDate(source, key));
    }
    
}
