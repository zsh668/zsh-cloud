package com.zsh.cloud.system.domain.model.log;

import com.zsh.cloud.common.core.domain.ValueObject;
import com.zsh.cloud.common.core.exception.code.enums.GlobalErrorCode;
import com.zsh.cloud.common.core.util.ServiceAssert;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 日志ID.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/21 17:10
 */
@Getter
public class LogId implements ValueObject<LogId> {
    
    /**
     * 日志ID.
     */
    private final String id;
    
    public LogId(String id) {
        ServiceAssert.isTrue(StringUtils.isNotBlank(id), GlobalErrorCode.BAD_REQUEST.getCode(), "日志id不能为空");
        this.id = id;
    }
    
    @Override
    public boolean sameValueAs(LogId other) {
        return other != null && this.id.equals(other.id);
    }
    
    @Override
    public String toString() {
        return id;
    }
}
