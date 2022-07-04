package com.zsh.cloud.common.core.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.ImmutableMap;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Collection 工具类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/4 09:37
 */
@UtilityClass
public class CollectionUtils {
    
    /**
     * 是否包含.
     *
     * @param source
     * @param targets
     * @return
     */
    public static boolean containsAny(Object source, Object... targets) {
        return Arrays.asList(targets).contains(source);
    }
    
    /**
     * 包含
     *
     * @param source
     * @param candidates
     * @return
     */
    public static boolean containsAny(Collection<?> source, Collection<?> candidates) {
        return org.springframework.util.CollectionUtils.containsAny(source, candidates);
    }
    
    /**
     * 是否存在空
     *
     * @param collections
     * @return
     */
    public static boolean isAnyEmpty(Collection<?>... collections) {
        return Arrays.stream(collections).anyMatch(CollectionUtil::isEmpty);
    }
    
    /**
     * 过滤.
     *
     * @param from
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> List<T> filterList(Collection<T> from, Predicate<T> predicate) {
        if (CollUtil.isEmpty(from)) {
            return new ArrayList<>();
        }
        return from.stream().filter(predicate).collect(Collectors.toList());
    }
    
    /**
     * 去重.
     *
     * @param from
     * @param keyMapper
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> List<T> distinct(Collection<T> from, Function<T, R> keyMapper) {
        if (CollUtil.isEmpty(from)) {
            return new ArrayList<>();
        }
        return distinct(from, keyMapper, (t1, t2) -> t1);
    }
    
    /**
     * 去重.
     *
     * @param from
     * @param keyMapper
     * @param cover
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> List<T> distinct(Collection<T> from, Function<T, R> keyMapper, BinaryOperator<T> cover) {
        if (CollUtil.isEmpty(from)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(convertMap(from, keyMapper, Function.identity(), cover).values());
    }
    
    /**
     * 转换、过滤.
     *
     * @param from
     * @param func
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> List<U> convertList(Collection<T> from, Function<T, U> func) {
        if (CollUtil.isEmpty(from)) {
            return new ArrayList<>();
        }
        return from.stream().map(func).filter(Objects::nonNull).collect(Collectors.toList());
    }
    
    /**
     * 过滤、转换.
     *
     * @param from
     * @param func
     * @param filter
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> List<U> convertList(Collection<T> from, Function<T, U> func, Predicate<T> filter) {
        if (CollUtil.isEmpty(from)) {
            return new ArrayList<>();
        }
        return from.stream().filter(filter).map(func).filter(Objects::nonNull).collect(Collectors.toList());
    }
    
    /**
     * 转换、过滤.
     *
     * @param from
     * @param func
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> Set<U> convertSet(Collection<T> from, Function<T, U> func) {
        if (CollUtil.isEmpty(from)) {
            return new HashSet<>();
        }
        return from.stream().map(func).filter(Objects::nonNull).collect(Collectors.toSet());
    }
    
    /**
     * 过滤、转换.
     *
     * @param from
     * @param func
     * @param filter
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> Set<U> convertSet(Collection<T> from, Function<T, U> func, Predicate<T> filter) {
        if (CollUtil.isEmpty(from)) {
            return new HashSet<>();
        }
        return from.stream().filter(filter).map(func).filter(Objects::nonNull).collect(Collectors.toSet());
    }
    
    /**
     * 转换.
     *
     * @param from
     * @param keyFunc
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> Map<K, T> convertMap(Collection<T> from, Function<T, K> keyFunc) {
        if (CollUtil.isEmpty(from)) {
            return new HashMap<>();
        }
        return convertMap(from, keyFunc, Function.identity());
    }
    
    /**
     * 转换.
     *
     * @param from
     * @param keyFunc
     * @param supplier
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> Map<K, T> convertMap(Collection<T> from, Function<T, K> keyFunc,
            Supplier<? extends Map<K, T>> supplier) {
        if (CollUtil.isEmpty(from)) {
            return supplier.get();
        }
        return convertMap(from, keyFunc, Function.identity(), supplier);
    }
    
    /**
     * 转换.
     *
     * @param from
     * @param keyFunc
     * @param valueFunc
     * @param <T>
     * @param <K>
     * @param <V>
     * @return
     */
    public static <T, K, V> Map<K, V> convertMap(Collection<T> from, Function<T, K> keyFunc, Function<T, V> valueFunc) {
        if (CollUtil.isEmpty(from)) {
            return new HashMap<>();
        }
        return convertMap(from, keyFunc, valueFunc, (v1, v2) -> v1);
    }
    
    /**
     * 转换.
     *
     * @param from
     * @param keyFunc
     * @param valueFunc
     * @param mergeFunction
     * @param <T>
     * @param <K>
     * @param <V>
     * @return
     */
    public static <T, K, V> Map<K, V> convertMap(Collection<T> from, Function<T, K> keyFunc, Function<T, V> valueFunc,
            BinaryOperator<V> mergeFunction) {
        if (CollUtil.isEmpty(from)) {
            return new HashMap<>();
        }
        return convertMap(from, keyFunc, valueFunc, mergeFunction, HashMap::new);
    }
    
    /**
     * 转换.
     *
     * @param from
     * @param keyFunc
     * @param valueFunc
     * @param supplier
     * @param <T>
     * @param <K>
     * @param <V>
     * @return
     */
    public static <T, K, V> Map<K, V> convertMap(Collection<T> from, Function<T, K> keyFunc, Function<T, V> valueFunc,
            Supplier<? extends Map<K, V>> supplier) {
        if (CollUtil.isEmpty(from)) {
            return supplier.get();
        }
        return convertMap(from, keyFunc, valueFunc, (v1, v2) -> v1, supplier);
    }
    
    /**
     * 转换.
     *
     * @param from
     * @param keyFunc
     * @param valueFunc
     * @param mergeFunction
     * @param supplier
     * @param <T>
     * @param <K>
     * @param <V>
     * @return
     */
    public static <T, K, V> Map<K, V> convertMap(Collection<T> from, Function<T, K> keyFunc, Function<T, V> valueFunc,
            BinaryOperator<V> mergeFunction, Supplier<? extends Map<K, V>> supplier) {
        if (CollUtil.isEmpty(from)) {
            return new HashMap<>();
        }
        return from.stream().collect(Collectors.toMap(keyFunc, valueFunc, mergeFunction, supplier));
    }
    
    /**
     * 分组.
     *
     * @param from
     * @param keyFunc
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> Map<K, List<T>> convertMultiMap(Collection<T> from, Function<T, K> keyFunc) {
        if (CollUtil.isEmpty(from)) {
            return new HashMap<>();
        }
        return from.stream().collect(Collectors.groupingBy(keyFunc, Collectors.mapping(t -> t, Collectors.toList())));
    }
    
    /**
     * 分组.
     *
     * @param from
     * @param keyFunc
     * @param valueFunc
     * @param <T>
     * @param <K>
     * @param <V>
     * @return
     */
    public static <T, K, V> Map<K, List<V>> convertMultiMap(Collection<T> from, Function<T, K> keyFunc,
            Function<T, V> valueFunc) {
        if (CollUtil.isEmpty(from)) {
            return new HashMap<>();
        }
        return from.stream()
                .collect(Collectors.groupingBy(keyFunc, Collectors.mapping(valueFunc, Collectors.toList())));
    }
    
    /**
     * 分组.
     *
     * @param from
     * @param keyFunc
     * @param valueFunc
     * @param <T>
     * @param <K>
     * @param <V>
     * @return
     */
    public static <T, K, V> Map<K, Set<V>> convertMultiMap2(Collection<T> from, Function<T, K> keyFunc,
            Function<T, V> valueFunc) {
        if (CollUtil.isEmpty(from)) {
            return new HashMap<>();
        }
        return from.stream().collect(Collectors.groupingBy(keyFunc, Collectors.mapping(valueFunc, Collectors.toSet())));
    }
    
    /**
     * 转换.
     *
     * @param from
     * @param keyFunc
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> Map<K, T> convertImmutableMap(Collection<T> from, Function<T, K> keyFunc) {
        if (CollUtil.isEmpty(from)) {
            return Collections.emptyMap();
        }
        ImmutableMap.Builder<K, T> builder = ImmutableMap.builder();
        from.forEach(item -> builder.put(keyFunc.apply(item), item));
        return builder.build();
    }
    
    /**
     * 第一个.
     *
     * @param from
     * @param <T>
     * @return
     */
    public static <T> T getFirst(List<T> from) {
        return !CollectionUtil.isEmpty(from) ? from.get(0) : null;
    }
    
    /**
     * 第一个
     *
     * @param from
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> T findFirst(List<T> from, Predicate<T> predicate) {
        if (CollUtil.isEmpty(from)) {
            return null;
        }
        return from.stream().filter(predicate).findFirst().orElse(null);
    }
    
    /**
     * 获取最大
     *
     * @param from
     * @param valueFunc
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V extends Comparable<? super V>> V getMaxValue(List<T> from, Function<T, V> valueFunc) {
        if (CollUtil.isEmpty(from)) {
            return null;
        }
        assert from.size() > 0; // 断言，避免告警
        T t = from.stream().max(Comparator.comparing(valueFunc)).get();
        return valueFunc.apply(t);
    }
    
    /**
     * 增加 非空.
     *
     * @param coll
     * @param item
     * @param <T>
     */
    public static <T> void addIfNotNull(Collection<T> coll, T item) {
        if (item == null) {
            return;
        }
        coll.add(item);
    }
    
    /**
     * 单
     *
     * @param deptId
     * @param <T>
     * @return
     */
    public static <T> Collection<T> singleton(T deptId) {
        return deptId == null ? Collections.emptyList() : Collections.singleton(deptId);
    }
    
}
