package com.zsh.cloud.common.swagger2.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.net.HttpHeaders;
import com.zsh.cloud.common.core.constant.CommonConstant;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

/**
 * Swagger配置
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/11 12:06
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@ConditionalOnClass({Docket.class, ApiInfoBuilder.class})
// 允许使用 swagger.enable=false 禁用 Swagger
@ConditionalOnProperty(prefix = "zsh.cloud.swagger", value = "enable", matchIfMissing = true)
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    public SwaggerProperties swaggerProperties() {
        return new SwaggerProperties();
    }
    
    @Bean
    public Docket createRestApi() {
        SwaggerProperties properties = swaggerProperties();
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo(properties)).select()
                .apis(basePackage(properties.getBasePackage())).paths(PathSelectors.any()).build()
                .securitySchemes(security()).globalRequestParameters(getGlobalRequestParameters())
                .securityContexts(securityContexts());
    }
    
    /**
     * API 摘要信息
     */
    private static ApiInfo apiInfo(SwaggerProperties properties) {
        return new ApiInfoBuilder().title(properties.getTitle()).description(properties.getDescription())
                .termsOfServiceUrl(properties.getTermsOfServiceUrl())
                .contact(new Contact(properties.getAuthor(), null, null)).version(properties.getVersion()).build();
    }
    
    private List<SecurityScheme> security() {
        ApiKey apiKey = new ApiKey(HttpHeaders.AUTHORIZATION, CommonConstant.AUTH_KEY, In.HEADER.toValue());
        return Collections.singletonList(apiKey);
    }
    
    /**
     * 授权信息全局应用
     */
    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(SecurityContext.builder().securityReferences(Collections.singletonList(
                new SecurityReference(CommonConstant.AUTH_KEY,
                        new AuthorizationScope[] {new AuthorizationScope("global", "")}))).build());
    }
    
    /**
     * 生成全局通用参数
     *
     * @return
     */
    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder().name("tenant_id").description("租户id").required(true)
                .in(ParameterType.HEADER).query(q -> q.model(m -> m.scalarModel(ScalarType.STRING))).required(false)
                .build());
        return parameters;
    }
}
