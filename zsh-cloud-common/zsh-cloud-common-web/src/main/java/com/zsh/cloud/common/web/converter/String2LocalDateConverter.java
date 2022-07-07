package com.zsh.cloud.common.web.converter;

import com.zsh.cloud.common.core.util.DateUtil;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 解决入参为 LocalDate类型.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/7 14:45
 */
public class String2LocalDateConverter extends BaseDateConverter<LocalDate> implements Converter<String, LocalDate> {
    
    protected static final Map<String, String> FORMAT = new LinkedHashMap(2);
    
    static {
        FORMAT.put(DateUtil.DEFAULT_DATE_FORMAT, "^\\d{4}-\\d{1,2}-\\d{1,2}$");
        FORMAT.put(DateUtil.DEFAULT_DATE_FORMAT_SLASH, "^\\d{4}/\\d{1,2}/\\d{1,2}$");
    }
    
    @Override
    protected Map<String, String> getFormat() {
        return FORMAT;
    }
    
    @Override
    public LocalDate convert(String source) {
        return super.convert(source, (key) -> LocalDate.parse(source, DateTimeFormatter.ofPattern(key)));
    }
    
}

