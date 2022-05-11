package com.zsh.cloud.system.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.common.mybatis.conditions.query.LbqwExt;
import com.zsh.cloud.common.mybatis.mapper.BaseMapperExt;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysUserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
                .eq(SysUserDO::getStatus, StatusEnum.ENABLE.getValue())
                // 未删除
                .eq(SysUserDO::getDelFlag, StatusEnum.DISABLE.getValue()));
    }
}
