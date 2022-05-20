package com.zsh.cloud.system.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsh.cloud.system.domain.model.resource.ResourceRepository;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysResourceDO;
import com.zsh.cloud.system.infrastructure.persistence.mapper.SysResourceMapper;
import org.springframework.stereotype.Repository;

/**
 * 资源-Repository实现类.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/20 13:44
 */
@Repository
public class ResourceRepositoryImpl extends ServiceImpl<SysResourceMapper, SysResourceDO>
        implements ResourceRepository, IService<SysResourceDO> {
    
}
