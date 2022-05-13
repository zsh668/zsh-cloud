package com.zsh.cloud.system.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zsh.cloud.common.core.domain.Page;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.mybatis.datascope.annotations.DataScope;
import com.zsh.cloud.common.mybatis.core.query.LbqwExt;
import com.zsh.cloud.common.mybatis.core.mapper.BaseMapperExt;
import com.zsh.cloud.common.mybatis.util.MyBatisUtils;
import com.zsh.cloud.common.mybatis.util.PageAssembler;
import com.zsh.cloud.system.application.query.UserPageQuery;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 用户Mapper.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/25 09:48
 */
@Mapper
public interface SysUserMapper extends BaseMapperExt<SysUserDO> {
    
    /**
     * 查询用户(不包含租户)
     *
     * @param account
     * @return
     */
    @InterceptorIgnore(tenantLine = "true")
    default List<SysUserDO> queryUserNoTenantByAccount(String account) {
        return selectList(new LbqwExt<SysUserDO>().eq(SysUserDO::getAccount, account)
                .eq(SysUserDO::getStatus, StatusEnum.ENABLE.getValue()));
    }
    
    /**
     * 根据查询条件分页查询数据.
     *
     * @param query  分页参数
     * @param orgIds
     * @return
     */
    @DataScope
    default Page<SysUserDO> selectPage(UserPageQuery query, Collection<String> orgIds) {
        return selectPage(query, new LbqwExt<SysUserDO>().likeIfPresent(SysUserDO::getAccount, query.getAccount())
                .likeIfPresent(SysUserDO::getUserName, query.getUserName()).inIfPresent(SysUserDO::getOrgId, orgIds));
    }
}
