package com.zsh.cloud.common.mybatis.datascope.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据权限.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/5/12 18:42
 */
@Data
public class DataPermission implements Serializable {
    
    /**
     * 组织权限字段.
     */
    private String scopeName = "org_id";
    
    /**
     * 用户权限字段.
     */
    private String selfScopeName = "created_by";
    
    /**
     * 组织ID集合.
     */
    private List<String> orgIds = new ArrayList<>();
    
    /**
     * 所属权限类型. 默认个人
     */
    private Integer dsType;
}
