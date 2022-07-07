package com.zsh.cloud.common.web.converter;

import com.zsh.cloud.common.core.util.DateUtil;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 解决入参为 LocalTime类型.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/7 14:45
 */
public class String2LocalTimeConverter extends BaseDateConverter<LocalTime> implements Converter<String, LocalTime> {
    
    protected static final Map<String, String> FORMAT = new LinkedHashMap(1);
    
    static {
        FORMAT.put(DateUtil.DEFAULT_TIME_FORMAT, "^\\d{1,2}:\\d{1,2}:\\d{1,2}$");
    }
    
    @Override
    protected Map<String, String> getFormat() {
        return FORMAT;
    }
    
    @Override
    public LocalTime convert(String source) {
        return super.convert(source, (key) -> LocalTime.parse(source, DateTimeFormatter.ofPattern(key)));
    }
}
