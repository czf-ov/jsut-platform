package com.czf.common.swagger.constant;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Scott
 * @Date: 2020/12/10 21:58
 */
@ConfigurationProperties("swagger")
@Data
public class SwaggerProperties {

    /**
     * 标题
     */
    private String title = "";

    private String description = "";

    private String version = "";

    private String licence = "";

    private String licenceUrl = "";

    private String host = "";
    /**
     * swagger会解析的包路径
     **/
    private String basePackage = "";

    private List<String> excludePaths = new ArrayList<>();

    private List<String> baseUrl = new ArrayList<>();

    private Authorization authorization = new Authorization();

    @Data
    @NoArgsConstructor
    public class Authorization {

        private String name = "";

        private String authRegex = "^.*$";

        private List<AuthorizationScope> authorizationScopes = new ArrayList<>();

        private List<String> tokenUrlList = new ArrayList<>();

    }

    @Data
    @NoArgsConstructor
    public class AuthorizationScope {

        private String scope = "";

        private String description = "";
    }
}
