package com.zsh.cloud.common.core.domain;

import lombok.Data;

import java.util.List;

/**
 * 树节点 继承此类然后使用ListUtil.treeify()进行list转树操作
 *
 * @author hang
 * @version 1.0
 * @date 2022/05/13 10:13
 */
@Data
public class TreeNode<T extends TreeNode<T>> {
    
    private Long id;
    
    private Long parentId;
    
    private List<T> childList;
}
