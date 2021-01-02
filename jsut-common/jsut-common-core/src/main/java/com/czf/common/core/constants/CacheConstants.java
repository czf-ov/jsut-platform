package com.czf.common.core.constants;

import static com.czf.common.core.constants.GlobalConstants.GLOBAL_PREFIX;

/**
 * @Author: Scott
 * @Date: 2020/12/26 19:49
 *
 *
 */
public interface CacheConstants {

    /**
     * oauth2 token 前缀
     */
    String OAUTH_TOKEN_PREFIX = GLOBAL_PREFIX + "oauth:";


    /**
     * oauth2 client detail前缀
     */
    String OAUTH_CLIENT_DETAIL = GLOBAL_PREFIX + "client:detail:";
}
