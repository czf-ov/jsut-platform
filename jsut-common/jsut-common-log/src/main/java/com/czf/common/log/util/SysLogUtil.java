package com.czf.common.log.util;

import cn.hutool.http.HttpUtil;
import com.czf.common.log.constant.LogTypeConstants;
import com.czf.entity.SysLog;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @Author: Scott
 * @Date: 2020/12/19 19:10
 */
@UtilityClass
public class SysLogUtil {


    public SysLog getLogInfo(String title) {

        /**
         * Objects.requireNonNull 若参数为null，则抛出NullPointException
         *  RequestContextHolder 持有request上下文的容器
         */
        HttpServletRequest servletRequest = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        SysLog sysLog = new SysLog();
        sysLog.setServiceId(getClientId());
        sysLog.setTitle(title);
        sysLog.setType(LogTypeConstants.NORMAL);
        sysLog.setMethod(servletRequest.getMethod());
        sysLog.setRemoteAddr(servletRequest.getRemoteAddr());
        sysLog.setRequestUri(servletRequest.getRequestURI());
        sysLog.setUserAgent(servletRequest.getHeader("user-agent"));
        sysLog.setParams(HttpUtil.toParams(servletRequest.getParameterMap()));
        sysLog.setCreateBy(getUserName());
        sysLog.setCreateTime(LocalDateTime.now());
        return null;
    }


    /**
     * 获取oauth2 ClientId
     *
     * @return
     */
    private String getClientId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof OAuth2Authentication) {
            return ((OAuth2Authentication) authentication).getOAuth2Request().getClientId();
        }

        return null;
    }

    /**
     * 获取
     * Principal 是一个接口，主要用来表示一个主体抽象概念，可以是一个实体，登录id，公司等..
     * This interface represents the abstract notion of a principal, which
     * can be used to represent any entity, such as an individual, a
     * corporation, and a login id.
     *
     * @return
     */
    private String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        return authentication.getName();
    }

}
