package com.kuehnenagel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MessageController {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	
}
