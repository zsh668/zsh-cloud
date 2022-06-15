package com.zsh.cloud.common.core.domain;

import com.zsh.cloud.common.core.dto.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 树节点 继承此类然后使用ListUtil.treeify()进行list转树操作
 *
 * @author hang
 * @version 1.0
 * @date 2022/05/13 10:13
 */
@Data
public class TreeNode<T extends TreeNode<T, I>, I extends Serializable> extends DTO {
    
    /**
     * id.
     */
    private I id;
    
    /**
     * 父id.
     */
    private I parentId;
    
    /**
     * 子集合.
     */
    private List<T> childList;
}
