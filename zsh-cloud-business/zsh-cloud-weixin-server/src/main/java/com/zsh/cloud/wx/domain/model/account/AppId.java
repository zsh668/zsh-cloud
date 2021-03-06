package com.zsh.cloud.wx.domain.model.account;

import com.zsh.cloud.common.core.domain.ValueObject;
import org.springframework.util.ObjectUtils;

/**
 * appid
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/16 10:04
 */
public class AppId implements ValueObject<AppId> {
    
    private String id;
    
    public AppId(final String id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new IllegalArgumentException("appid不能为空");
        }
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    
    @Override
    public boolean sameValueAs(AppId other) {
        return other != null && this.id.equals(other.id);
    }
    
    @Override
    public String toString() {
        return id;
    }
}
