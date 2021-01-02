package com.czf.common.security.service;

import com.czf.common.core.constants.CacheConstants;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * @Author: Scott
 * @Date: 2020/12/26 21:19
 */
public class JsutClientDetailService extends JdbcClientDetailsService {


    public JsutClientDetailService(DataSource dataSource) {
        super(dataSource);
    }


    /**
     * 设置原生方法支持缓存
     *
     * @param clientId
     * @return
     * @throws InvalidClientException
     */

    @Override
    @Cacheable(value = CacheConstants.OAUTH_CLIENT_DETAIL, key = "#clientId", unless = "#result == null ")
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        return super.loadClientByClientId(clientId);
    }
}
