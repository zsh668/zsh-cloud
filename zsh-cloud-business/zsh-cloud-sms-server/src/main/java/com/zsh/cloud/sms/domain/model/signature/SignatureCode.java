package com.zsh.cloud.sms.domain.model.signature;

import com.zsh.cloud.common.core.domain.ValueObject;
import com.zsh.cloud.common.core.exception.code.enums.GlobalErrorCode;
import com.zsh.cloud.common.core.util.ServiceAssert;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * 签名编码.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/25 17:43
 */
@Getter
public class SignatureCode implements ValueObject<SignatureCode> {
    
    private static final Pattern VALID_PATTERN = Pattern.compile("^[A-Za-z0-9]+$");
    
    /**
     * 签名编码.
     */
    private String code;
    
    public SignatureCode(final String code) {
        ServiceAssert.isTrue(StringUtils.isNotBlank(code), GlobalErrorCode.BAD_REQUEST.getCode(), "签名编码不能为空");
        ServiceAssert.isTrue(VALID_PATTERN.matcher(code).matches(), GlobalErrorCode.BAD_REQUEST.getCode(), "签名编码格式不正确");
        this.code = code;
    }
    
    @Override
    public boolean sameValueAs(SignatureCode other) {
        return other != null && this.code.equals(other.code);
    }
    
    @Override
    public String toString() {
        return code;
    }
}
