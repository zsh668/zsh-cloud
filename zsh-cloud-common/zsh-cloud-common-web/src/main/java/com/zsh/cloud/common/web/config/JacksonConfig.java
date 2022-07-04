package com.zsh.cloud.common.web.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zsh.cloud.common.core.util.DateUtil;
import com.zsh.cloud.common.web.converter.EnumDeserializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 解决序列化和反序列化.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/4 14:51
 */
@Configuration
public class JacksonConfig {
    
    /**
     * 解决序列化和反序列化时，参数转换问题
     * </p>
     * addSerializer：序列化 （Controller 返回 给前端的json）
     * </p>
     * Long -> string
     * </p>
     * BigInteger -> string
     * </p>
     * BigDecimal -> string
     * </p>
     * date -> string
     * </p>
     * addDeserializer: 反序列化 （前端调用接口时，传递到后台的json）
     * </p>
     * 枚举类型: {"code": "xxx"} -> Enum
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        builder.simpleDateFormat(DateUtil.FULL_TIME_SPLIT_PATTERN);
        ObjectMapper objectMapper = builder.createXmlMapper(false)
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .timeZone(TimeZone.getTimeZone("Asia/Shanghai")).build();
        
        objectMapper.setLocale(Locale.CHINA).configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                // 忽略未知字段
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                // 该特性决定parser是否允许JSON字符串包含非引号控制字符（值小于32的ASCII字符，包含制表符和换行符）。
                // 如果该属性关闭，则如果遇到这些字符，则会抛出异常。JSON标准说明书要求所有控制符必须使用引号，因此这是一个非标准的特性
                .configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true)
                // 忽略不能转移的字符
                .configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true)
                // 单引号处理
                .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
                // 日期格式
                .setDateFormat(new SimpleDateFormat(DateUtil.FULL_TIME_SPLIT_PATTERN));
        
        // 反序列化时，属性不存在的兼容处理
        objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        
        SimpleModule simpleModule = new SimpleModule().addSerializer(Long.class, ToStringSerializer.instance)
                .addSerializer(Long.TYPE, ToStringSerializer.instance)
                .addSerializer(BigInteger.class, ToStringSerializer.instance)
                .addSerializer(BigDecimal.class, ToStringSerializer.instance)
                .addDeserializer(Enum.class, EnumDeserializer.INSTANCE);
        
        return objectMapper.registerModule(simpleModule);
    }
}
