package com.mp.websocket;

import com.mp.model.UserInfo;
import org.apache.log4j.Logger;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component
public class MyHandshakeInterreptor implements HandshakeInterceptor {
    public static Logger logger = Logger.getLogger(MyHandshakeInterreptor.class);
    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {

        System.out.println("Before Handshake++++++++++++++++++++++++");
        if (request instanceof ServletServerHttpRequest){
            ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) request;
            //如果session不存在，不会新建session
            HttpSession session = servletServerHttpRequest.getServletRequest().getSession(false);
            if (session != null){
                logger.info("成功建立");
                UserInfo userInfo = (UserInfo) session.getAttribute("UserInfo");
                logger.info(userInfo.getUserRealname());
                attributes.put("UserInfo",userInfo);
            }
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception ex) {
        System.out.println("After Handshake++++++++++++++++++++");

    }

}
