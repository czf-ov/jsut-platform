package com.czf.auth.config;

import com.czf.auth.SecurityConstants;
import com.czf.common.core.constants.CacheConstants;
import com.czf.common.security.entity.JsutUser;
import com.czf.common.security.service.JsutClientDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Scott
 * @Date: 2020/12/26 13:40
 *
 * 认证服务器
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

    /**
     * 配置端口访问安全性
     * checkTokenAccess 参考 {@Link SecurityExpressionOperations}中的表达式
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients().checkTokenAccess("isAuthenticated");
    }

    /**
     * 配置客户端及其属性
     * withClientDetails 替换为自定义客户端信息服务
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        JsutClientDetailService clientDetailService = new JsutClientDetailService(dataSource);
        clientDetailService.setSelectClientDetailsSql(SecurityConstants.DEFAULT_SELECT_STATEMENT);
        clientDetailService.setFindClientDetailsSql(SecurityConstants.DEFAULT_FIND_STATEMENT);
        clients.withClientDetails(clientDetailService);
    }

    /**
     * 配置授权服务器暴露端口的非安全访问
     * 设置允许访问的请求方式
     * 设置token的存储
     * 设置refreshToken是否允许，当refresh_token去刷新你access_token的时候，是否允许刷新refresh_token
     * 为password访问模式提供设置认证管理器
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenStore(tokenStore())
                .reuseRefreshTokens(Boolean.FALSE)
                .authenticationManager(authenticationManager)
                .tokenEnhancer(tokenEnhancer());
        // todo 设置异常处理

    }

    /**
     * 设置token增加附加信息
     * userType: 标明用户类型
     * identity: 标明用户id
     *
     * @return
     */
    private TokenEnhancer tokenEnhancer() {
        Map<String, Object> additionInfo = new HashMap<>();
        return (accessToken, authentication) -> {
            Authentication userAuthentication = authentication.getUserAuthentication();
            if (userAuthentication != null) {
                JsutUser user = (JsutUser) userAuthentication.getPrincipal();
                if (user != null) {
                    additionInfo.put("userType", user.getUserType());
                    additionInfo.put("identity", user.getIdentity());
                }
            }
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionInfo);
            return accessToken;
        };

    }

    private TokenStore tokenStore() {
        // todo add redis config
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setPrefix(CacheConstants.OAUTH_TOKEN_PREFIX);
        return redisTokenStore;
    }
}
