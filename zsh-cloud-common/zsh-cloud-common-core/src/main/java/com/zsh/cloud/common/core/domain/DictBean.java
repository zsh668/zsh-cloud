package com.zsh.cloud.common.core.domain;

import lombok.Data;

/**
 * 字典bean 只有code和text，可用于展示下拉框
 *
 * @author hang
 * @version 1.0
 * @date 2022/05/13 10:13
 */
@Data
public class DictBean<T> implements IDict<T> {
    
    private final T code;
    
    private final String text;
}