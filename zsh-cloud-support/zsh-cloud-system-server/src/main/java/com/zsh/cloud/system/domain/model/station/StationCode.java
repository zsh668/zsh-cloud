package com.zsh.cloud.system.domain.model.station;

import com.zsh.cloud.common.core.domain.ValueObject;
import com.zsh.cloud.common.core.exception.code.enums.GlobalErrorCode;
import com.zsh.cloud.common.core.util.ServiceAssert;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * 岗位编码.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 11:53
 */
public class StationCode implements ValueObject<StationCode> {
    
    private static final Pattern VALID_PATTERN = Pattern.compile("^[A-Za-z0-9]+$");
    
    /**
     * 岗位编码.
     */
    private final String code;
    
    public StationCode(final String code) {
        ServiceAssert.isTrue(StringUtils.isNotBlank(code), GlobalErrorCode.BAD_REQUEST.getCode(), "岗位编码不能为空");
        ServiceAssert.isTrue(VALID_PATTERN.matcher(code).matches(), GlobalErrorCode.BAD_REQUEST.getCode(), "岗位编码格式不正确");
        this.code = code;
    }
    
    public String getCode() {
        return code;
    }
    
    @Override
    public boolean sameValueAs(StationCode other) {
        return other != null && this.code.equals(other.code);
    }
    
    @Override
    public String toString() {
        return code;
    }
}
