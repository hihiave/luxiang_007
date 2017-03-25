package com.mp.websocket;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by 95 on 2017/1/5.
 */
@Configuration
@EnableWebMvc
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    private Logger logger = Logger.getLogger(WebSocketConfig.class);
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config){
        logger.info("connect to server");
        config.enableSimpleBroker("/topic","/user");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user/");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/new_event").withSockJS();
        stompEndpointRegistry.addEndpoint("/web_socket");
    }
}
