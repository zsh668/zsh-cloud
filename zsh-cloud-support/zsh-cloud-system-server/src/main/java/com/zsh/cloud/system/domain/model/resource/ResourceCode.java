package com.zsh.cloud.system.domain.model.resource;

import com.zsh.cloud.common.core.domain.ValueObject;
import com.zsh.cloud.common.core.exception.code.enums.GlobalErrorCode;
import com.zsh.cloud.common.core.util.ServiceAssert;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 资源编码.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/25 17:43
 */
@Getter
public class ResourceCode implements ValueObject<ResourceCode> {
    
    /**
     * 资源编码.
     */
    private String code;
    
    public ResourceCode(final String code) {
        ServiceAssert.isTrue(StringUtils.isNotBlank(code), GlobalErrorCode.BAD_REQUEST.getCode(), "资源编码不能为空");
        this.code = code;
    }
    
    @Override
    public boolean sameValueAs(ResourceCode other) {
        return other != null && this.code.equals(other.code);
    }
    
    @Override
    public String toString() {
        return code;
    }
}
