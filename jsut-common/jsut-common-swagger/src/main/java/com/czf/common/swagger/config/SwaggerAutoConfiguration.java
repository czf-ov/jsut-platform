package com.czf.common.swagger.config;

import com.czf.common.swagger.constant.SwaggerProperties;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: Scott
 * @Date: 2020/12/10 22:03
 */

@Configuration
@EnableSwagger2
@EnableConfigurationProperties(SwaggerProperties.class)
@ConditionalOnProperty(name = "swagger.enabled", matchIfMissing = true)
@Profile({"dev", "test"})
public class SwaggerAutoConfiguration {

    @Bean
    public Docket docket(SwaggerProperties swaggerProperties) {


        // exclude-path 处理
        List<Predicate<String>> excludePaths = new ArrayList<>();
        swaggerProperties.getExcludePaths().forEach(excludePath -> {
            excludePaths.add(PathSelectors.ant(excludePath));
        });

        // normal url
        List<Predicate<String>> baseUrls = new ArrayList<>();
        swaggerProperties.getBaseUrl().forEach(baseUrl -> {
            baseUrls.add(PathSelectors.ant(baseUrl));
        });

        /**
         * securitySchemes 与 securityContexts的设置是为了让api拥有鉴权访问的功能
         * 参考 http://www.leftso.com/blog/393.html
         */

        return new Docket(DocumentationType.SWAGGER_2)
                .host(swaggerProperties.getHost())
                .apiInfo(apiInfo(swaggerProperties))
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                .paths(Predicates.and(Predicates.not(Predicates.or(excludePaths)), Predicates.and(baseUrls)))
                .build()
                .securitySchemes(Collections.singletonList(securitySchemes(swaggerProperties)))
                .securityContexts(Collections.singletonList(securityContexts(swaggerProperties)));
    }

    private SecurityContext securityContexts(SwaggerProperties swaggerProperties) {
        return SecurityContext.builder()
                .securityReferences(defaultReference(swaggerProperties))
                .forPaths(PathSelectors.regex(swaggerProperties.getAuthorization().getAuthRegex()))
                .build();
    }

    /**
     * 默认鉴权策略
     *
     * @param swaggerProperties
     * @return
     */
    private List<SecurityReference> defaultReference(SwaggerProperties swaggerProperties) {
        List<AuthorizationScope> authorizationScopeList = new ArrayList<>();

        swaggerProperties.getAuthorization().getAuthorizationScopes().forEach(authorizationScope -> {
            authorizationScopeList.add(new AuthorizationScope(authorizationScope.getScope(), authorizationScope.getDescription()));
        });

        AuthorizationScope[] authorizationScopes = new AuthorizationScope[authorizationScopeList.size()];
        return Collections.singletonList(SecurityReference.builder()
                .scopes(authorizationScopeList.toArray(authorizationScopes))
                .reference(swaggerProperties.getAuthorization().getName())
                .build());
    }

    private OAuth securitySchemes(SwaggerProperties swaggerProperties) {
        List<AuthorizationScope> authorizationScopes = new ArrayList<>();
        List<GrantType> grantTypes = new ArrayList<>();
        swaggerProperties.getAuthorization().getAuthorizationScopes().forEach(authorizationScope -> {
            authorizationScopes.add(new AuthorizationScope(authorizationScope.getScope(), authorizationScope.getDescription()));
        });
        swaggerProperties.getAuthorization().getTokenUrlList().forEach(tokenUrl -> {
            grantTypes.add(new ResourceOwnerPasswordCredentialsGrant(tokenUrl));
        });
        return new OAuth(swaggerProperties.getAuthorization().getName(), authorizationScopes, grantTypes);
    }


    private ApiInfo apiInfo(SwaggerProperties swaggerProperties) {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .version(swaggerProperties.getVersion())
                .license(swaggerProperties.getLicence())
                .licenseUrl(swaggerProperties.getLicenceUrl())
                .build();
    }


}

/**
 * # swagger 配置\n
 * swagger:
 * title: Pig Swagger API
 * license: Powered By pig4cloud
 * licenseUrl: https://pig4cloud.com
 * terms-of-service-url: https://pig4cloud.com
 * contact:
 * email: wangiegie@gmail.com
 * url: https://pig4cloud.com
 * authorization:
 * name: pig4cloud OAuth
 * auth-regex: ^.*$
 * authorization-scope-list:
 * - scope: server
 * description: server all
 * token-url-list:
 * - http://${GATEWAY_HOST:pig-gateway}:${GATEWAY-PORT:9999}/auth/oauth/token
 **/