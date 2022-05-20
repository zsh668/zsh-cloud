package com.zsh.cloud.common.log.enums;

import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.domain.ValueObject;

/**
 * 日志类型-枚举.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/19 15:46
 */
public enum LogTypeEnum implements ValueObject<LogTypeEnum>, IDict<String> {
    
    /**
     * OPT="操作类型"
     */
    OPT("OPT", "正常"),
    
    /**
     * EX="异常类型"
     */
    EX("EX", "异常");
    
    LogTypeEnum(String code, String text) {
        init(code, text);
    }
    
    @Override
    public boolean sameValueAs(LogTypeEnum other) {
        return false;
    }
}
