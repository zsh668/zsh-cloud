package com.zsh.cloud.system.infrastructure.persistence.mapper;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.mybatis.core.mapper.BaseMapperExt;
import com.zsh.cloud.common.mybatis.util.Wraps;
import com.zsh.cloud.system.application.model.query.LoginLogPageQuery;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysLoginLogDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登录日志 Mapper.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 14:39
 */
@Mapper
public interface SysLoginLogMapper extends BaseMapperExt<SysLoginLogDO> {
    
    /**
     * 根据查询条件分页查询数据.
     *
     * @param query
     * @return
     */
    default Page<SysLoginLogDO> selectPage(LoginLogPageQuery query) {
        return selectPage(query, Wraps.<SysLoginLogDO>lbQ().likeIfPresent(SysLoginLogDO::getAccount, query.getAccount())
                .likeIfPresent(SysLoginLogDO::getUserName, query.getUserName())
                .likeIfPresent(SysLoginLogDO::getRequestIp, query.getRequestIp())
                .leFooterIfPresent(SysLoginLogDO::getLoginTime, query.getStartLoginTime())
                .geHeaderIfPresent(SysLoginLogDO::getLoginTime, query.getEndLoginTime())
                .orderByDesc(SysLoginLogDO::getId));
    }
}
