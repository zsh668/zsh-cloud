package com.zsh.cloud.common.core.util;

import com.zsh.cloud.common.core.domain.TreeNode;
import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * List工具类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/12 19:13
 */
@UtilityClass
public class ListUtils {
    
    // 父ID
    private static final String PARENT_ID = "0";
    
    /**
     * 匹配两个list集合相同元素，并执行匹配后的动作 适用于两个集合泛型不一致
     *
     * @param list1    第一个集合
     * @param list2    第二个集合
     * @param field1   第一个集合中判定元素相同的属性，如：SomeBean::getId
     * @param field2   第二个集合中判定元素相同的属性，如：OtherBean::getId
     * @param operator 匹配后执行的动作，入参为A、B集合中的相同元素，如：(a, b)->a.setXx(b.getXx())
     * @param <T>      第一个集合的泛型
     * @param <K>      第二个集合的泛型
     */
    public static <T, K> void match(Collection<T> list1, Collection<K> list2, Function<T, ?> field1,
            Function<K, ?> field2, BiConsumer<T, K> operator) {
        Map<?, K> map = list2.stream().collect(Collectors.toMap(field2, Function.identity()));
        list1.forEach(item1 -> Optional.ofNullable(map.get(field1.apply(item1)))
                .ifPresent(item2 -> operator.accept(item1, item2)));
    }
    
    /**
     * 匹配两个list集合中的相同元素，并执行匹配后的动作 适用于两个集合的泛型一致
     *
     * @param list1      第一个集合
     * @param list2      第二个集合
     * @param equalField 判定元素相同的属性，如Student::getId
     * @param operator   匹配后执行的动作，入参为两个集合中的相同元素，如：(a, b)->a.setXx(b.getXx())
     * @param <T>        集合泛型
     */
    public static <T> void match(Collection<T> list1, Collection<T> list2, Function<T, ?> equalField,
            BiConsumer<T, T> operator) {
        match(list1, list2, equalField, equalField, operator);
    }
    
    /**
     * 转换List
     *
     * @param list      原集合
     * @param converter 转换函数
     * @return 转换后的函数
     */
    public static <T, R> List<R> convert(List<T> list, Function<T, R> converter) {
        return list.stream().map(converter).collect(Collectors.toList());
    }
    
    /**
     * 转换List，转换之后去重
     *
     * @param list      原集合
     * @param converter 转换函数
     * @return 转换后的函数
     */
    public static <T, R extends Long> List<R> convertDistinct(List<T> list, Function<T, R> converter) {
        return list.stream().map(converter).distinct().collect(Collectors.toList());
    }
    
    /**
     * List转为树.
     *
     * @param source list
     * @return 树化后的List
     */
    public static <T extends TreeNode<T, String>> List<T> treeify(List<T> source) {
        return treeify(source, PARENT_ID);
    }
    
    /**
     * List转为树.
     *
     * @param source list
     * @param rootId 指定树的根节点id，一般为0
     * @return 树化后的List
     */
    public static <T extends TreeNode<T, I>, I extends Serializable> List<T> treeify(List<T> source, I rootId) {
        
        if (CollectionUtils.isEmpty(source)) {
            return new ArrayList<>();
        }
        final List<T> result = new ArrayList<>();
        final Map<Object, T> map = new HashMap<>(source.size());
        // 获取根节点，map赋值。
        source.forEach(node -> {
            if (Objects.equals(rootId, node.getParentId())) {
                result.add(node);
            }
            map.put(node.getId(), node);
        });
        // childList赋值
        source.forEach(node -> map.computeIfPresent(node.getParentId(), (parentId, parentNode) -> {
            Optional.ofNullable(parentNode.getChildren()).orElseGet(() -> {
                final List<T> list = new ArrayList<>();
                parentNode.setChildren(list);
                return list;
            }).add(node);
            return parentNode;
        }));
        // 处理 无父ID的集合,根节点除外
        source.forEach(node -> {
            if (!Objects.equals(rootId, node.getParentId()) && !map.containsKey(node.getParentId())) {
                result.add(map.get(node.getId()));
            }
        });
        return result;
    }
}
