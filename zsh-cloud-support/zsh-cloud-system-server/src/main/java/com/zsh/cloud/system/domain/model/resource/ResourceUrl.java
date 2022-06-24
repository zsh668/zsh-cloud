package com.zsh.cloud.system.domain.model.resource;

import com.zsh.cloud.common.core.domain.ValueObject;
import com.zsh.cloud.common.core.exception.code.enums.GlobalErrorCode;
import com.zsh.cloud.common.core.util.ServiceAssert;
import org.apache.commons.lang3.StringUtils;

/**
 * 资源地址.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 11:53
 */
public class ResourceUrl implements ValueObject<ResourceUrl> {
    
    /**
     * 资源地址.
     */
    private final String url;
    
    public ResourceUrl(final String url) {
        ServiceAssert.isTrue(StringUtils.isNotBlank(url), GlobalErrorCode.BAD_REQUEST.getCode(), "资源地址不能为空");
        this.url = url;
    }
    
    public String getUrl() {
        return url;
    }
    
    @Override
    public boolean sameValueAs(ResourceUrl other) {
        return other != null && this.url.equals(other.url);
    }
    
    @Override
    public String toString() {
        return url;
    }
}
