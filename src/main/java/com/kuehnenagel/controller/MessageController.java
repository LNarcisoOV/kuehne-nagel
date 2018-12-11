package com.kuehnenagel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.kuehnenagel.interfaces.JmsDispatcherInterface;
import com.kuehnenagel.model.StockLevel;
import com.kuehnenagel.util.UtilConverter;

@RestController
@RequestMapping("/")
public class MessageController {
	
	@Autowired
	private JmsDispatcherInterface jmsDispatcherInterface;
	
	@GetMapping
	public ModelAndView initialPage() {
		ModelAndView modelAndView = new ModelAndView("html/login/loginForm");
		return modelAndView;
	}

	@ResponseBody
	@PostMapping(value="sendMessageQueue/", produces="application/xml", consumes="application/xml")
	public StockLevel sendMessageQueue(@RequestBody StockLevel stockLevel) {
		try{
			String xmlString = UtilConverter.convertObjectInXmlString(stockLevel);
			jmsDispatcherInterface.sendMessage("kuehnenagel.queue.sample", xmlString);
		}catch(Exception e) {
			
		}finally {
			return stockLevel;
		}
	}
	
	@ResponseBody
	@PostMapping(value="sendMessageTopic/", produces="application/xml", consumes="application/xml")
	public StockLevel sendMessageTopic(@RequestBody StockLevel stockLevel) {
		try{
			String xmlString = UtilConverter.convertObjectInXmlString(stockLevel);
			jmsDispatcherInterface.sendMessage("kuehnenagel.topic.sample", xmlString);
		}catch(Exception e) {
			
		}finally {
			return stockLevel;
		}
	}
}
