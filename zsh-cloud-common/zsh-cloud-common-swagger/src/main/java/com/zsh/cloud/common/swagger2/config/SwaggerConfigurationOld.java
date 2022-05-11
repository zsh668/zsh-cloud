//package com.zsh.cloud.common.swagger2.config;
//
//import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
//import com.google.common.net.HttpHeaders;
//import com.zsh.cloud.common.core.constant.CommonConstant;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.models.auth.In;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.util.ReflectionUtils;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
//import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.builders.RequestParameterBuilder;
//import springfox.documentation.schema.ScalarType;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.ApiKey;
//import springfox.documentation.service.AuthorizationScope;
//import springfox.documentation.service.Contact;
//import springfox.documentation.service.ParameterType;
//import springfox.documentation.service.RequestParameter;
//import springfox.documentation.service.SecurityReference;
//import springfox.documentation.service.SecurityScheme;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
//import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * Swagger配置
// *
// * @author zhangshuhang
// * @version 1.0
// * @date 2022/4/11 12:06
// */
//@Configuration
//@EnableSwagger2
//@EnableKnife4j
//@Import(BeanValidatorPluginsConfiguration.class)
//public class SwaggerConfigurationOld implements WebMvcConfigurer {
//
//    /**
//     *
//     * 显示swagger-ui.html文档展示页，还必须注入swagger资源：
//     *
//     * @param registry
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
//
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo()).select()
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths(PathSelectors.any())
//                .build().securitySchemes(security()).securityContexts(securityContexts())
//                .globalRequestParameters(getGlobalRequestParameters());
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder().title("zsh-cloud").description("zsh-cloud是一款基于DDD领域模型设计并支持SaaS平台的微服务框架")
//                .termsOfServiceUrl("http://www.zsh6.com").contact(new Contact("zsh", "", "")).version("v1.0.0").build();
//    }
//
//    private List<SecurityScheme> security() {
//        ApiKey apiKey = new ApiKey(HttpHeaders.AUTHORIZATION, CommonConstant.AUTH_KEY, In.HEADER.toValue());
//        return Collections.singletonList(apiKey);
//    }
//
//    /**
//     * 授权信息全局应用
//     */
//    private List<SecurityContext> securityContexts() {
//        return Collections.singletonList(SecurityContext.builder().securityReferences(Collections.singletonList(
//                new SecurityReference(CommonConstant.AUTH_KEY,
//                        new AuthorizationScope[] {new AuthorizationScope("global", "")}))).build());
//    }
//
//    /**
//     * 生成全局通用参数
//     *
//     * @return
//     */
//    private List<RequestParameter> getGlobalRequestParameters() {
//        List<RequestParameter> parameters = new ArrayList<>();
//        parameters.add(new RequestParameterBuilder().name("tenant_id").description("租户id").required(true)
//                .in(ParameterType.HEADER).query(q -> q.model(m -> m.scalarModel(ScalarType.STRING))).required(false)
//                .build());
//        return parameters;
//    }
//
//    /**
//     * 解决springboot2.6 和springfox不兼容问题
//     * @return
//     */
//    @Bean
//    public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
//        return new BeanPostProcessor() {
//
//            @Override
//            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//                if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
//                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
//                }
//                return bean;
//            }
//
//            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
//                List<T> copy = mappings.stream()
//                        .filter(mapping -> mapping.getPatternParser() == null)
//                        .collect(Collectors.toList());
//                mappings.clear();
//                mappings.addAll(copy);
//            }
//
//            @SuppressWarnings("unchecked")
//            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
//                try {
//                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
//                    field.setAccessible(true);
//                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
//                } catch (IllegalArgumentException | IllegalAccessException e) {
//                    throw new IllegalStateException(e);
//                }
//            }
//        };
//    }
//}
