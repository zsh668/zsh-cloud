package com.zsh.cloud.system.domain.specification;

import com.zsh.cloud.common.core.domain.specification.AbstractSpecification;
import com.zsh.cloud.common.core.exception.code.enums.ServiceErrorCode;
import com.zsh.cloud.common.core.util.Assert;
import com.zsh.cloud.common.core.util.ServiceAssert;
import com.zsh.cloud.system.domain.model.org.Org;
import com.zsh.cloud.system.domain.model.org.OrgRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * 组织创建Specification.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/6/16 10:05
 */
public class OrgCreateSpecification extends AbstractSpecification<Org> {
    
    private OrgRepository orgRepository;
    
    public OrgCreateSpecification(OrgRepository orgRepository) {
        this.orgRepository = orgRepository;
    }
    
    @Override
    public boolean isSatisfiedBy(Org org) {
        List<Org> orgs = orgRepository.find(org.getOrgName());
        if (!CollectionUtils.isEmpty(orgs)) {
            orgs.forEach(o -> {
                // 同一组织下，名称不能重复
                boolean expression =
                        !o.getOrgId().sameValueAs(org.getOrgId()) && o.getParentId().sameValueAs(org.getParentId())
                                && o.getOrgName().sameValueAs(org.getOrgName());
                Assert.notTrue(expression, ServiceErrorCode.ORG_NAME_EXISTS);
            });
        }
        // 父组织不能为空
        if (!StringUtils.equals(Org.PARENT_ID, org.getParentId().getId())) {
            Org parent = orgRepository.find(org.getParentId());
            ServiceAssert.notNull(parent, ServiceErrorCode.ORG_NOT_EXISTS.getCode(), "父组织不存在");
        }
        // 同级组织
        orgs = orgRepository.queryList(org.getParentId());
        orgs.forEach(o -> {
            // 同级组织下，排序不能重复
            boolean expression =
                    !o.getOrgId().sameValueAs(org.getOrgId()) && Objects.equals(org.getSortValue(), o.getSortValue());
            Assert.notTrue(expression, ServiceErrorCode.ORG_SORT_EXISTS);
        });
        return true;
    }
}
