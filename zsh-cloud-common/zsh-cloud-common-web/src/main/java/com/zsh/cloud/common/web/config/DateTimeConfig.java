package com.zsh.cloud.common.web.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.zsh.cloud.common.core.util.DateUtil;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
                        new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateUtil.FULL_TIME_SPLIT_PATTERN)))
                .serializerByType(LocalDate.class,
                        new LocalDateSerializer(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_DATE_FORMAT)))
                .serializerByType(LocalTime.class,
                        new LocalTimeSerializer(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_TIME_FORMAT)))
                .deserializerByType(LocalDateTime.class,
                        new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DateUtil.FULL_TIME_SPLIT_PATTERN)))
                .deserializerByType(LocalDate.class,
                        new LocalDateDeserializer(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_DATE_FORMAT)))
                .deserializerByType(LocalTime.class,
                        new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DateUtil.DEFAULT_TIME_FORMAT)));
    }
    
    @Component
    public static class LocalDateConverter implements Converter<String, LocalDate> {
        
        @Override
        public LocalDate convert(@NonNull String source) {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern(DateUtil.DEFAULT_DATE_FORMAT));
        }
    }
    
    @Component
    public static class LocalDateTimeConverter implements Converter<String, LocalDateTime> {
        
        @Override
        public LocalDateTime convert(@NonNull String source) {
            return LocalDateTime.parse(source, DateTimeFormatter.ofPattern(DateUtil.FULL_TIME_SPLIT_PATTERN));
        }
    }
    
}
