package com.zsh.cloud.wx.infrastructure.persistence.converter;

import com.zsh.cloud.wx.domain.model.account.Account;
import com.zsh.cloud.wx.domain.model.account.Appid;
import com.zsh.cloud.wx.domain.model.account.Token;
import com.zsh.cloud.wx.infrastructure.persistence.entity.WxAccountDO;

/**
 * 公众号账号转换类
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/16 10:53
 */
public class AccountConverter {
    
    /**
     * 转换
     *
     * @param wxAccountDO
     * @return com.zsh.cloud.wx.domain.model.account.Account
     * @author hang
     * @date 2022/03/16 14:18
     */
    public static Account toAccount(WxAccountDO wxAccountDO) {
        if (wxAccountDO == null) {
            return null;
        }
        return new Account(new Appid(wxAccountDO.getAppid()), new Token(wxAccountDO.getToken()),
                wxAccountDO.getAesKey());
    }
}
