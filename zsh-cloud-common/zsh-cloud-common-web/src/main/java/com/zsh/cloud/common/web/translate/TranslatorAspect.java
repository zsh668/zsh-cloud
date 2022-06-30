package com.zsh.cloud.common.web.translate;

import com.zsh.cloud.common.core.cache.LocalCache;
import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.core.util.ReflectUtils;
import com.zsh.cloud.common.core.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 翻译 转换 AOP.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/13 17:21
 */
@Aspect
@Component
@Order(2)
@Slf4j
public class TranslatorAspect {
    
    @Resource
    private TranslateConvertFactory translateConvertFactory;
    
    @Pointcut("@annotation(com.zsh.cloud.common.web.translate.Translator)")
    public void pointCut() {
    }
    
    /**
     * 返回之后.
     *
     * @param joinPoint
     * @param object
     */
    @AfterReturning(pointcut = "pointCut()", returning = "object")
    @SuppressWarnings("unchecked")
    public void doAfter(JoinPoint joinPoint, Object object) {
        TranslateTypeConvert<Object> translateTypeConvert = null;
        if (object != null) {
            translateTypeConvert = translateConvertFactory.getTranslateTypeConvert((Class<Object>) object.getClass());
        }
        Object result;
        if (translateTypeConvert != null) {
            result = translateTypeConvert.convert(object);
        } else {
            result = object;
        }
        if (result instanceof Collection<?> || result instanceof Page) {
            Collection<?> collection = result instanceof Page ? ((Page<?>) result).getList() : (Collection<?>) result;
            if (collection.isEmpty()) {
                return;
            }
            collection.forEach((Consumer<Object>) this::translateObject);
            log.info("返回列表结果 {}", collection);
        } else {
            this.translateObject(object);
            log.info("返回对象结果 {}", object);
        }
    }
    
    private void translateObject(Object result) {
        if (Objects.isNull(result)) {
            return;
        }
        Field[] fields = ReflectUtils.getFields(result.getClass());
        // 翻译当前对象字段
        Arrays.stream(fields).filter(field -> field.getAnnotation(Translate.class) != null)
                .forEach(field -> translateField(result, field));
        // 翻译内部属性字段
        Arrays.stream(fields).filter(field -> field.getAnnotation(TranslateField.class) != null).forEach(field -> {
            try {
                Object subObject = ReflectUtils.invokeGet(result, field);
                if (subObject instanceof Collection<?>) {
                    ((Collection<?>) subObject).forEach(this::translateObject);
                } else {
                    this.translateObject(subObject);
                }
            } catch (Exception e) {
                log.error("翻译错误", e);
            }
        });
    }
    
    private List<Field> filterTranslate(Class<?> voType) {
        Field[] fields = voType.getDeclaredFields();
        return Arrays.stream(fields).filter(field -> field.getAnnotation(Translate.class) != null)
                .collect(Collectors.toList());
    }
    
    @SuppressWarnings("unchecked")
    private void translateField(Object vo, Field field) {
        try {
            Translate annotation = field.getAnnotation(Translate.class);
            Class<?> aClass = annotation.dataSource();
            Object fieldValue = ReflectUtils.invokeGet(vo, annotation.from());
            if (Objects.isNull(fieldValue)) {
                return;
            }
            String translate;
            if (IDict.class.isAssignableFrom(aClass)) {
                translate = new EnumTranslatable().translate(fieldValue, aClass, null);
            } else if (LocalCache.class.isAssignableFrom(annotation.dataSource())) {
                LocalCacheTranslator<Object, Object> localCacheTranslator = SpringUtils.getBean(
                        LocalCacheTranslator.class);
                translate = localCacheTranslator.translate(fieldValue, annotation.dataSource(), annotation.param());
            } else {
                Translatable<Object> translatable = SpringUtils.getBean(annotation.translator());
                translate = translatable.translate(fieldValue, annotation.dataSource(), annotation.param());
            }
            if (translate == null) {
                return;
            }
            ReflectUtils.invokeSet(vo, field, translate);
        } catch (Exception e) {
            log.error("翻译错误", e);
        }
    }
    
}
