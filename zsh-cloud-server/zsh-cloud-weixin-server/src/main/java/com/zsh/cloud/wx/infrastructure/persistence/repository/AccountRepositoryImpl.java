package com.zsh.cloud.wx.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.wx.domain.model.account.Appid;
import com.zsh.cloud.wx.domain.model.account.Account;
import com.zsh.cloud.wx.domain.model.account.AccountRepository;
import com.zsh.cloud.wx.infrastructure.persistence.converter.AccountConverter;
import com.zsh.cloud.wx.infrastructure.persistence.entity.WxAccountDO;
import com.zsh.cloud.wx.infrastructure.persistence.mapper.WxAccountMapper;
import org.springframework.stereotype.Repository;

/**
 * 公众号账号-Repository实现类
 *
 * @author hang
 * @version 1.0
 * @date 2022/03/15 18:29
 */
@Repository
public class AccountRepositoryImpl extends ServiceImpl<WxAccountMapper, WxAccountDO>
        implements AccountRepository, IService<WxAccountDO> {
    
    @Override
    public Account find(Appid appid) {
        WxAccountDO wxAccountDO = this.getById(appid.getId());
        return AccountConverter.toAccount(wxAccountDO);
    }
}
