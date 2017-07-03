package com.niit.collaborationbackend.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {
	private static final Logger log = LoggerFactory.getLogger(ChatController.class);

	@RequestMapping("/chat")
	@SendTo("/topic/message")
	public OutputMessage sedMessage(Message message)
	{
		log.debug("Calling the method sedMessage()");
		log.debug("Message id :" + message.getId());
		log.debug("Message    : " + message.getMessage());
		
		return new  OutputMessage(message, new Date());
	}
}