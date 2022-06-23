package com.zsh.cloud.common.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * json工具类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/7 16:32
 */
@UtilityClass
@Slf4j
public class JsonUtils {
    
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    static {
        // 解决不支持java8的时间类型
        MAPPER.registerModule(new JavaTimeModule());
        // 解决 空对象报错
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 解决字段对应不上报错
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    
    /**
     * 对象转json.
     *
     * @param object
     * @return
     */
    public static String toJsonString(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("object parse err", e);
            throw new RuntimeException(e);
        }
    }
    
    /**
     * json转对象.
     *
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String text, Class<T> clazz) {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        try {
            return MAPPER.readValue(text, clazz);
        } catch (IOException e) {
            log.error("json parse err,json:{}", text, e);
            throw new RuntimeException(e);
        }
    }
    
    /**
     * json转对象.
     *
     * @param text
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String text, TypeReference<T> typeReference) {
        try {
            return MAPPER.readValue(text, typeReference);
        } catch (IOException e) {
            log.error("json parse err,json:{}", text, e);
            throw new RuntimeException(e);
        }
    }
}
