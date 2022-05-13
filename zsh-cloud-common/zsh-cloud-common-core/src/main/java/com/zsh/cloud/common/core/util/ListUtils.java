package com.zsh.cloud.common.core.util;

import com.zsh.cloud.common.core.domain.TreeNode;
import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
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
    
    /**
     * 匹配两个list，指定它们之间关联的属性以及匹配后的动作
     * </p>
     * 简单来说，就是另一个集合 给 主集合 的 字段赋值.
     *
     * @param mainList                  主集合
     * @param otherList                 另一个集合
     * @param mainField                 主集合中的关联属性
     * @param otherFieldEqualsMainField 另一个集合中的关联属性
     * @param operator                  匹配后的动作
     * @param <T>                       主集合中的元素
     * @param <K>                       另一个集合中的元素
     * @param <R>                       关联属性的类型
     */
    public static <T, K, R> void match(List<T> mainList, List<K> otherList, Function<T, R> mainField,
            Function<K, R> otherFieldEqualsMainField, BiConsumer<T, K> operator) {
        Map<R, K> map = otherList.stream().collect(Collectors.toMap(otherFieldEqualsMainField, Function.identity()));
        for (T t : mainList) {
            operator.accept(t, map.get(mainField.apply(t)));
        }
    }
    
    /**
     * 转换List.
     *
     * @param list      原集合
     * @param converter 转换函数
     * @return 转换后的函数
     */
    public static <T, R> List<R> convert(List<T> list, Function<T, R> converter) {
        return list.stream().map(converter).collect(Collectors.toList());
    }
    
    /**
     * 转换List，转换之后去重.
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
     * @param rootId 指定树的根节点id，一般为0
     * @return 树化后的List
     */
    public static <T extends TreeNode<T>> List<T> treeify(List<T> source, Long rootId) {
        
        if (CollectionUtils.isEmpty(source)) {
            return new ArrayList<>();
        }
        final List<T> result = new ArrayList<>();
        final Map<Object, T> map = new HashMap<>(source.size());
        source.forEach(node -> {
            if (Objects.equals(rootId, node.getParentId())) {
                result.add(node);
            }
            map.put(node.getId(), node);
        });
        source.forEach(node -> map.computeIfPresent(node.getParentId(), (parentId, parentNode) -> {
            Optional.ofNullable(parentNode.getChildList()).orElseGet(() -> {
                final List<T> list = new ArrayList<>();
                parentNode.setChildList(list);
                return list;
            }).add(node);
            return parentNode;
        }));
        return result;
    }
}
