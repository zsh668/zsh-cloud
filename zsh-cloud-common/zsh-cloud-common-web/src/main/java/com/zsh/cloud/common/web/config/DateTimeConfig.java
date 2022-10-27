package com.zsh.cloud.common.web.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.zsh.cloud.common.core.util.DateUtil;
import com.zsh.cloud.common.web.converter.String2DateConverter;
import com.zsh.cloud.common.web.converter.String2LocalDateConverter;
import com.zsh.cloud.common.web.converter.String2LocalDateTimeConverter;
import com.zsh.cloud.common.web.converter.String2LocalTimeConverter;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期转换配置，全局统一.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/13 16:18
 */
@Configuration
public class DateTimeConfig {
    
    @Bean
    @Primary
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder.serializerByType(LocalDateTime.class,
                        new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_DATE_TIME_FORMAT)))
                .serializerByType(LocalDate.class,
                        new LocalDateSerializer(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_DATE_FORMAT)))
                .serializerByType(LocalTime.class,
                        new LocalTimeSerializer(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_TIME_FORMAT)))
                .deserializerByType(LocalDateTime.class,
                        new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_DATE_TIME_FORMAT)))
                .deserializerByType(LocalDate.class,
                        new LocalDateDeserializer(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_DATE_FORMAT)))
                .deserializerByType(LocalTime.class,
                        new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_TIME_FORMAT)));
    }
    
    /**
     * 解决 @RequestParam(value = "date") Date date date 类型参数 格式问题
     *
     * @return
     */
    @Bean
    public Converter<String, Date> dateConvert() {
        return new String2DateConverter();
    }
    
    /**
     * 解决 @RequestParam(value = "time") LocalDate time
     *
     * @return
     */
    @Bean
    public Converter<String, LocalDate> localDateConverter() {
        return new String2LocalDateConverter();
    }
    
    /**
     * 解决 @RequestParam(value = "time") LocalTime time
     *
     * @return
     */
    @Bean
    public Converter<String, LocalTime> localTimeConverter() {
        return new String2LocalTimeConverter();
    }
    
    /**
     * 解决 @RequestParam(value = "time") LocalDateTime time
     *
     * @return
     */
    @Bean
    public Converter<String, LocalDateTime> localDateTimeConverter() {
        return new String2LocalDateTimeConverter();
    }
    
}
