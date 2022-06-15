package com.zsh.cloud.system.infrastructure.persistence.converter;

import com.zsh.cloud.common.core.domain.IDict;
import com.zsh.cloud.common.core.enums.StatusEnum;
import com.zsh.cloud.system.domain.model.org.Org;
import com.zsh.cloud.system.domain.model.org.OrgId;
import com.zsh.cloud.system.domain.model.org.OrgName;
import com.zsh.cloud.system.infrastructure.persistence.entity.SysOrgDO;

/**
 * 组织Converter.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/15 15:44
 */
public class OrgConverter {
    
    /**
     * 转换.
     *
     * @param sysOrgDO
     * @return
     */
    public static Org toOrg(SysOrgDO sysOrgDO) {
        if (sysOrgDO == null) {
            return null;
        }
        return new Org(new OrgId(sysOrgDO.getId()), new OrgName(sysOrgDO.getOrgName()),
                new OrgId(sysOrgDO.getParentId()), sysOrgDO.getSortValue(),
                IDict.getByCode(StatusEnum.class, sysOrgDO.getStatus()), sysOrgDO.getDescribe());
    }
}
