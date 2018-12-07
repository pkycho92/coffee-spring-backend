package coffee.web;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.*;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatServer extends TextWebSocketHandler {

	private static final Set<WebSocketSession> peers = Collections.synchronizedSet(new HashSet<WebSocketSession>());

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		peers.add(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		peers.remove(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		for (WebSocketSession peer : peers) {
			peer.sendMessage(message);
		}
	}
}