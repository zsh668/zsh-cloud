package com.zsh.cloud.common.mybatis.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zsh.cloud.common.core.util.RequestUtils;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 通用参数填充实现类
 * <p></p>
 * 如果没有显式的对通用参数进行赋值，这里会对通用参数进行填充、赋值.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/3/23 15:42
 */
public class DefaultFieldHandler implements MetaObjectHandler {
    
    private static final String CREATED_TIME = "createdTime";
    
    private static final String CREATED_BY = "createdBy";
    
    private static final String UPDATED_TIME = "updatedTime";
    
    private static final String UPDATED_BY = "updatedBy";
    
    private static final String IS_DELETED = "delFlag";
    
    private static final String DEFAULT_USER_ID = "0";
    
    private String getCurrentUserId() {
        try {
            return RequestUtils.getUserId();
        } catch (Exception e) {
            return DEFAULT_USER_ID;
        }
    }
    
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, CREATED_TIME, LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, CREATED_BY, this::getCurrentUserId, String.class);
        this.strictInsertFill(metaObject, IS_DELETED, () -> Boolean.FALSE, Boolean.class);
    }
    
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, UPDATED_TIME, LocalDateTime::now, LocalDateTime.class);
        this.strictUpdateFill(metaObject, UPDATED_BY, this::getCurrentUserId, String.class);
    }
}
