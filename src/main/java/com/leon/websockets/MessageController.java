package com.leon.websockets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController
{
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	// Our method receives messages from the /chat-app/message endpoint.
	@MessageMapping("/message")
	// Send the return value to client apps subscribed to the "/chat-topic/messages" endpoint.
	@SendTo("/chat-topic/messages")
	public MessageDto processMessageFromClient(MessageDto messageDto)
	{
		logger.info("Message from client: {}", messageDto.getMessage());
		return messageDto;
	}
}
