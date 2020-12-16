package com.czf.msg.config;

import com.czf.msg.constant.StompProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

/**
 * @Author: Scott
 * @Date: 2020/12/13 14:43
 */
@Configuration
@EnableConfigurationProperties(StompProperties.class)
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private StompProperties stompProperties;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // todo
        registry.addEndpoint("/ws").setAllowedOrigins("*").setHandshakeHandler(defaultHandshakeHandler());
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableStompBrokerRelay("/queue")
                .setClientLogin(stompProperties.getUserName())
                .setClientPasscode(stompProperties.getPassword())
                .setRelayHost(stompProperties.getServer())
                .setRelayPort(stompProperties.getPort())
                .setSystemLogin(stompProperties.getServer())
                .setSystemPasscode(stompProperties.getPassword())
                .setAutoStartup(Boolean.TRUE);

    }

    private DefaultHandshakeHandler defaultHandshakeHandler() {
        return null;
    }
}
