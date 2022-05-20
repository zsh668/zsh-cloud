package com.zsh.cloud.system.application.translate;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.common.core.util.SpringUtils;
import com.zsh.cloud.common.web.translate.Translatable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * 通用翻译(根据主键查询实体，获取某字段值).
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/18 18:31
 */
@Slf4j
@Component
public class ServiceImplTranslator implements Translatable<String> {
    
    @Override
    public String translate(String original, Class<?> datasource, String param) {
        if (ServiceImpl.class.isAssignableFrom(datasource)) {
            ServiceImpl<?, ?> service = (ServiceImpl<?, ?>) SpringUtils.getBean(datasource);
            Object bean = service.getById(original);
            Field field;
            try {
                if (bean == null) {
                    return null;
                }
                field = bean.getClass().getDeclaredField(param);
                field.setAccessible(true);
                return (String) field.get(bean);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                log.error("serviceImpl 翻译失败 ", e);
            }
        }
        return null;
    }
}
