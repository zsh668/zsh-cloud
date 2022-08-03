package com.zsh.cloud.common.swagger2.config;

import com.google.common.net.HttpHeaders;
import com.zsh.cloud.common.core.constant.CommonConstant;
import com.zsh.cloud.common.swagger2.core.SpringFoxHandlerProviderBeanPostProcessor;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Swagger配置.
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/11 12:06
 */
@Configuration
@EnableOpenApi
public class SwaggerConfiguration {
    
    @Value("${spring.application.name}")
    private String applicationName;
    
    @Bean
    public SpringFoxHandlerProviderBeanPostProcessor springFoxHandlerProviderBeanPostProcessor() {
        return new SpringFoxHandlerProviderBeanPostProcessor();
    }
    
    @Bean
    public Docket createRestApi() {
        // RequestHandlerSelectors.withClassAnnotation(Api.class) 不显示登录接口
        // RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class) 显示登录接口
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).paths(PathSelectors.any()).build()
                .securitySchemes(security()).globalRequestParameters(getGlobalRequestParameters())
                .securityContexts(securityContexts());
    }
    
    /**
     * API 摘要信息.
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(applicationName).description(applicationName + "接口文档。")
                .contact(new Contact("zsh", "https://www.zsh6.com", "zhangshuhang@zsh6.com")).version("1.0").build();
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
