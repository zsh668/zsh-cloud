package com.zsh.cloud.common.core.constant;

/**
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/13 11:12
 */
public interface AuthConstants {
    
    /**
     * JWT存储权限前缀
     */
    String AUTHORITY_PREFIX = "ROLE_";
    
    /**
     * JWT存储权限属性
     */
    String JWT_AUTHORITIES_KEY = "authorities";
    
    /**
     * 认证请求头key
     */
    String AUTHORIZATION_KEY = "Authorization";
    
    /**
     * JWT令牌前缀
     */
    String AUTHORIZATION_PREFIX = "Bearer ";
    
    /**
     * JWT载体key
     */
    String JWT_PAYLOAD_KEY = "payload";
    
    /**
     * JWT ID 唯一标识
     */
    String JWT_JTI = "jti";
    
    String CLIENT_DETAILS_FIELDS = "client_id, CONCAT('{noop}', client_secret) as client_secret, resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";
    
    String BASE_CLIENT_DETAILS_SQL = "select " + CLIENT_DETAILS_FIELDS + " from oauth_client_details";
    
    String FIND_CLIENT_DETAILS_SQL = BASE_CLIENT_DETAILS_SQL + " order by client_id";
    
    String SELECT_CLIENT_DETAILS_SQL = BASE_CLIENT_DETAILS_SQL + " where client_id = ?";
    
    String USER_ID_KEY = "userId";
    
    String USER_NAME_KEY = "userName";
    
    String TENANT_ID_KEY = "tenantId";
    
    /**
     * 认证地址
     */
    String OAUTH_TOKEN = "/oauth/token";
    
    /**
     *
     */
    String GRANT_TYPE_KEY = "grant_type";
    
    /**
     *
     */
    String REFRESH_TOKEN = "refresh_token";
    
    /**
     * 密码
     */
    String PASSWORD = "password";
    
    /**
     * 密码加密方式
     */
    String BCRYPT = "{bcrypt}";
    
    /**
     * 验证码
     */
    String VALIDATE_CODE_CODE = "code";
    
    /**
     * 验证码 key
     */
    String VALIDATE_CODE_KEY = "key";
}
