package com.leon.websockets;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // Enables WebSocket message handling, backed by a message broker.
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer
{
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry)
	{
		// Register STOMP endpoint at "/chatroom".
		// This endpoint will be used by the chat apps to connect to STOMP.
		// At this endpoint, the handover to websocket protocol from http protocol is done.
		registry.addEndpoint("/chatroom").setAllowedOrigins("*");
	}

	// The method configureMessageBroker() enables a simple memory-based message broker to carry
	// the messages back to the client on destinations prefixed with "/chat-topic".
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry)
	{
		// Set application destination prefix: /chat-app.
		// The client will send messages at this endpoint.
		// For example, if client sends message at /chat-app/chat,
		// the endpoint configured at /chat in the spring controller will be invoked.
		registry.setApplicationDestinationPrefixes("/chat-app");
		// Enable a simple message broker and configure a prefix to filter destinations targeting the broker.
		// The client app will subscribe messages at endpoints starting with these configured endpoint.
		registry.enableSimpleBroker("/chat-topic");
	}
}
