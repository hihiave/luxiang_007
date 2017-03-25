package com.mp.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.mp.model.UserInfo;
import com.mp.serviceimpl.UserInfoServiceImpl;

public class MessagePushHandler implements WebSocketHandler {
	public static Logger logger = Logger.getLogger(MessagePushHandler.class);
	private static final List<WebSocketSession> user_session_;
	@Autowired
	private UserInfoServiceImpl userInfoService;
	static {
		user_session_ = new ArrayList<WebSocketSession>();
	}

	@ResponseBody
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("connect to websocket success");
		System.out.println("connect to websocket success");
		user_session_.add(session);
		session.sendMessage(new TextMessage("connect to websocket success!!!"));
	}

	@Override
	public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage)
			throws Exception {
		UserInfo userInfo = (UserInfo) webSocketSession.getHandshakeAttributes().get("UserInfo");
		if (userInfo != null) {

		}
	}

	@Override
	public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
		if (webSocketSession.isOpen()) {
			webSocketSession.close();
		}
		logger.info("传输错误，关闭session");
		System.out.println("传输错误，关闭session");
		user_session_.remove(webSocketSession);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
		user_session_.remove(webSocketSession);
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	public void sendMessageToUser(int user_id, TextMessage message) {
		for (WebSocketSession user_session : user_session_) {
			UserInfo userInfo = (UserInfo) user_session.getHandshakeAttributes().get("UserInfo");
			if (userInfo.getUserId() == user_id) {
				try {
					if (user_session.isOpen()) {
						user_session.sendMessage(message);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
}
