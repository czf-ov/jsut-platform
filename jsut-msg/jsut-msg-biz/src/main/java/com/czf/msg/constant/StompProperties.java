package com.czf.msg.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: Scott
 * @Date: 2020/12/13 14:47
 */

@Data
@ConfigurationProperties(prefix = "stomp")
public class StompProperties {

    private String server;

    private Integer port;

    private String userName;

    private String password;
}
