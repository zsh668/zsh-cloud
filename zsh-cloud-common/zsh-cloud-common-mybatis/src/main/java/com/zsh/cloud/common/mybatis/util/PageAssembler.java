package com.zsh.cloud.common.mybatis.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zsh.cloud.common.core.domain.Page;

/**
 * 分页对象转换.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/3/23 11:54
 */
public class PageAssembler {
    
    /**
     * 对象转换.
     *
     * @param iPage 分页对象
     * @param <T>   实体
     * @return Page
     */
    public static <T> Page<T> toPage(IPage<T> iPage) {
        return new Page<>(iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getPages(), iPage.getRecords());
    }
}
