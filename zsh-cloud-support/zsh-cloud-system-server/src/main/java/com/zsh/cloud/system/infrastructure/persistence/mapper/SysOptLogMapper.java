package com.zsh.cloud.system.infrastructure.persistence.mapper;

import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.mybatis.core.mapper.BaseMapperExt;
import com.zsh.cloud.common.mybatis.util.Wraps;
import com.zsh.cloud.system.application.model.query.OptLogPageQuery;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysOptLogDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志 Mapper.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 14:39
 */
@Mapper
public interface SysOptLogMapper extends BaseMapperExt<SysOptLogDO> {
    
    /**
     * 根据查询条件分页查询数据.
     *
     * @param query
     * @return
     */
    default Page<SysOptLogDO> selectPage(OptLogPageQuery query) {
        return selectPage(query, Wraps.<SysOptLogDO>lbQ().likeIfPresent(SysOptLogDO::getUserName, query.getUserName())
                .likeIfPresent(SysOptLogDO::getRequestIp, query.getRequestIp())
                .likeIfPresent(SysOptLogDO::getType, query.getType())
                .likeIfPresent(SysOptLogDO::getHttpMethod, query.getHttpMethod())
                .leFooterIfPresent(SysOptLogDO::getStartTime, query.getStartOptTime())
                .geHeaderIfPresent(SysOptLogDO::getStartTime, query.getEndOptTime()).orderByDesc(SysOptLogDO::getId));
    }
}
