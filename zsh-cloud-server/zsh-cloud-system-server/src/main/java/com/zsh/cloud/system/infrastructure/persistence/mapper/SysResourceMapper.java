package com.zsh.cloud.system.infrastructure.persistence.mapper;

import com.zsh.cloud.common.mybatis.mapper.BaseMapperExt;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysResourceDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/29 11:06
 */
@Mapper
public interface SysResourceMapper extends BaseMapperExt<SysResourceDO> {
    
    List<SysResourceDO> queryResourceByUserId(@Param("userId") String userId);
}
