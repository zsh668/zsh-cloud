package com.zsh.cloud.common.mybatis.datascope.enums;

import com.zsh.cloud.common.core.domain.ValueObject;

/**
 * 数据权限范围枚举.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/12 11:21
 */
public enum DataScopeTypeEnum implements ValueObject<DataScopeTypeEnum> {
    
    ALL(1, "全部"),
    THIS_LEVEL(2, "本级"),
    THIS_LEVEL_CHILDREN(3, "本级以及子级"),
    CUSTOMIZE(4, "自定义"),
    SELF(5, "个人");
    
    /**
     * 值
     */
    private final int value;
    
    /**
     * 描述
     */
    private final String label;
    
    DataScopeTypeEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }
    
    /**
     * 根据匹配value的值获取Label.
     *
     * @param value
     * @return
     */
    public static String getLabelByValue(int value) {
        for (DataScopeTypeEnum s : DataScopeTypeEnum.values()) {
            if (value == s.getValue()) {
                return s.getLabel();
            }
        }
        return "";
    }
    
    public int getValue() {
        return value;
    }
    
    public String getLabel() {
        return label;
    }
    
    @Override
    public boolean sameValueAs(DataScopeTypeEnum other) {
        return this.equals(other);
    }
}
