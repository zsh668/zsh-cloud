package com.zsh.cloud.gateway.swagger;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger资源配置.
 * <p>
 * <@link https://doc.xiaominfo.com/knife4j/action/springcloud-gateway.html#_2-1-3-3-文档聚合业务编码>
 *
 * @author zhangshuhang
 * @version 1.0
 * @date 2022/4/21 16:50
 */
@Slf4j
@Component
@Primary
@AllArgsConstructor
public class SwaggerProvider implements SwaggerResourcesProvider {
    
    /**
     * 路径
     **/
    public static final String API_URI = "/v2/api-docs";
    
    public static final String OAS_URI = "/v3/api-docs";
    
    /**
     * 路由加载器
     **/
    private final RouteLocator routeLocator;
    
    /**
     * 网关配置
     **/
    private final GatewayProperties gatewayProperties;
    
    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routes = new ArrayList<>();
        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));
        gatewayProperties.getRoutes().stream().filter(routeDefinition -> routes.contains(routeDefinition.getId()))
                .forEach(route -> {
                    route.getPredicates().stream()
                            .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
                            .forEach(predicateDefinition -> resources.add(swaggerResource(route.getId(),
                                    predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0")
                                            .replace("/**", API_URI))));
                });
        
        return resources;
    }
    
    private SwaggerResource swaggerResource(String name, String location) {
        log.info("name:{},location:{}", name, location);
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(DocumentationType.OAS_30.getVersion());
        return swaggerResource;
    }
}