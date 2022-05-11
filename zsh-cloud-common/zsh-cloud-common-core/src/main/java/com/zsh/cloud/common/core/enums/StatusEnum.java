package com.zsh.cloud.common.core.enums;

import com.zsh.cloud.common.core.domain.ValueObject;

/**
 * 状态枚举.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/29 10:18
 */
public enum StatusEnum implements ValueObject<StatusEnum> {
    
    /**
     * 有效.
     */
    ENABLE(1, "有效"),
    
    /**
     * 禁用.
     */
    DISABLE(0, "禁用");
    
    private final int value;
    
    private final String label;
    
    StatusEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }
    
    public String getLabel() {
        return label;
    }
    
    public int getValue() {
        return value;
    }
    
    /**
     * 根据匹配value的值获取Label.
     *
     * @param value
     * @return
     */
    public static String getLabelByValue(int value) {
        for (StatusEnum s : StatusEnum.values()) {
            if (value == s.getValue()) {
                return s.getLabel();
            }
        }
        return "";
    }
    
    /**
     * 获取StatusEnum
     *
     * @param value
     * @return
     */
    public static StatusEnum getStatusEnum(int value) {
        for (StatusEnum s : StatusEnum.values()) {
            if (value == s.getValue()) {
                return s;
            }
        }
        return null;
    }
    
    @Override
    public boolean sameValueAs(final StatusEnum other) {
        return this.equals(other);
    }
}
