package com.zsh.cloud.system.domain.service.strategy;

import com.zsh.cloud.common.mybatis.datascope.enums.DataScopeTypeEnum;
import com.zsh.cloud.system.domain.model.org.OrgId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 创建环境角色Context.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/7/8 17:45
 */
@Service
public class DataScopeContext {
    
    private final Map<String, AbstractDataScopeHandler> strategyMap = new ConcurrentHashMap<>();
    
    /**
     * strategyMap 里边的key是指定其名字，这个会作为key放到strategyMap里.
     *
     * @param strategyMap
     */
    @Autowired
    public DataScopeContext(Map<String, AbstractDataScopeHandler> strategyMap) {
        strategyMap.forEach(this.strategyMap::put);
    }
    
    /**
     * 通过数据权限类型，查询最全、最新的组织id.
     *
     * @param orgIdList
     * @param dsType
     * @return
     */
    public Set<OrgId> getOrgIdsForDataScope(List<OrgId> orgIdList, DataScopeTypeEnum dsType, String userId) {
        AbstractDataScopeHandler handler = strategyMap.get(dsType.name());
        if (handler == null) {
            return Collections.emptySet();
        }
        return handler.getOrgIds(orgIdList, userId);
    }
    
    /**
     * 通过数据权限类型，查询最全、最新的组织id.
     *
     * @param roleId
     * @param dsType
     * @param userId
     * @return
     */
    public Set<String> getOrgIdsForDataScope(String roleId, DataScopeTypeEnum dsType, String userId) {
        AbstractDataScopeHandler handler = strategyMap.get(dsType.name());
        if (handler == null) {
            return Collections.emptySet();
        }
        return handler.getOrgIds(roleId, userId);
    }
}
