package com.zsh.cloud.common.web.converter;

import cn.hutool.core.util.ReflectUtil;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * enum反序列化工具.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/4 14:45
 */
@Slf4j
public class EnumDeserializer extends StdDeserializer<Enum<?>> {
    
    public static final EnumDeserializer INSTANCE = new EnumDeserializer();
    
    private static final String ALL_ENUM_STRING_CONVERT_METHOD = "valueOf";
    
    private static final String ALL_ENUM_KEY_FIELD = "name";
    
    public EnumDeserializer() {
        super(Enum.class);
    }
    
    @Override
    public Enum<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonToken token = p.getCurrentToken();
        String value = null;
        while (!token.isStructEnd()) {
            if (ALL_ENUM_KEY_FIELD.equals(p.getText())) {
                p.nextToken();
                value = p.getValueAsString();
            } else {
                p.nextToken();
            }
            token = p.getCurrentToken();
        }
        if (value == null || "".equals(value)) {
            return null;
        }
        
        Object obj = p.getCurrentValue();
        if (obj == null) {
            return null;
        }
        Field field = ReflectUtil.getField(obj.getClass(), p.getCurrentName());
        //找不到字段
        if (field == null) {
            return null;
        }
        Class<?> fieldType = field.getType();
        try {
            Method method = fieldType.getMethod(ALL_ENUM_STRING_CONVERT_METHOD, String.class);
            return (Enum<?>) method.invoke(null, value);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
            log.warn("解析枚举失败", e);
            return null;
        }
    }
}
