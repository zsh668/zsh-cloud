package com.zsh.cloud.common.core.util;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean 工具类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/22 15:23
 */
@Slf4j
public class BeanUtils<S, T> {
    
    public static Map<String, BeanCopier> beanCopierMap = new ConcurrentHashMap<>();
    
    /**
     * 转换单个实体
     *
     * @param source
     * @param modelClass
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> T copyBean(S source, Class<T> modelClass)
            throws IllegalAccessException, InstantiationException {
        if (source == null) {
            return null;
        }
        String beanKey = generateKey(source.getClass(), modelClass);
        BeanCopier copier = null;
        if (!beanCopierMap.containsKey(beanKey)) {
            copier = BeanCopier.create(source.getClass(), modelClass, false);
            beanCopierMap.put(beanKey, copier);
        } else {
            copier = beanCopierMap.get(beanKey);
        }
        Object model = modelClass.newInstance();
        copier.copy(source, model, null);
        return (T) model;
    }
    
    /**
     * 转换单个实体(无异常)
     *
     * @param source
     * @param modelClass
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> T copyBeanNoException(S source, Class<T> modelClass) {
        Object model = null;
        try {
            model = copyBean(source, modelClass);
        } catch (IllegalAccessException | InstantiationException e) {
            log.error("对象转换异常", e);
        }
        return (T) model;
    }
    
    /**
     * List集合对象转换
     *
     * @param fromList
     * @param tClass
     * @param <F>
     * @param <T>
     * @return
     */
    public static <F, T> List<T> copyListBean(List<F> fromList, Class<T> tClass)
            throws InstantiationException, IllegalAccessException {
        if (fromList == null || fromList.isEmpty()) {
            return null;
        }
        List<T> tList = new ArrayList<>();
        for (F f : fromList) {
            T t = copyBean(f, tClass);
            tList.add(t);
        }
        return tList;
    }
    
    private static String generateKey(Class<?> class1, Class<?> class2) {
        return class1.toString() + class2.toString();
    }
    
}
