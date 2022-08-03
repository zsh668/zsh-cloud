package com.zsh.cloud.sms.domain.model.signature;

import com.zsh.cloud.common.core.domain.ValueObject;
import com.zsh.cloud.common.core.exception.code.enums.GlobalErrorCode;
import com.zsh.cloud.common.core.util.ServiceAssert;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 签名ID.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 11:53
 */
@Getter
public class SignatureId implements ValueObject<SignatureId> {
    
    /**
     * 签名ID.
     */
    private final String id;
    
    public SignatureId(final String id) {
        ServiceAssert.isTrue(StringUtils.isNotBlank(id), GlobalErrorCode.BAD_REQUEST.getCode(), "签名id不能为空");
        this.id = id;
    }
    
    @Override
    public boolean sameValueAs(SignatureId other) {
        return other != null && this.id.equals(other.id);
    }
    
    @Override
    public String toString() {
        return id;
    }
}
