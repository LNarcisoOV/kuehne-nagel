package com.kuehnenagel.controller;

import java.io.StringWriter;

import javax.xml.bind.JAXB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.kuehnenagel.interfaces.JmsDispatcherInterface;
import com.kuehnenagel.model.StockLevel;

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
			String xmlString = convertObjectInXmlString(stockLevel);
			jmsDispatcherInterface.sendMessage("queue.sample", xmlString);
		}catch(Exception e) {
			
		}finally {
			return stockLevel;
		}
	}
	
	@ResponseBody
	@PostMapping(value="sendMessageTopic/", produces="application/xml", consumes="application/xml")
	public StockLevel sendMessageTopic(@RequestBody StockLevel stockLevel) {
		try{
			String xmlString = convertObjectInXmlString(stockLevel);
			jmsDispatcherInterface.sendMessage("topic.sample", xmlString);
		}catch(Exception e) {
			
		}finally {
			return stockLevel;
		}
	}
	
	public String convertObjectInXmlString(Object obj) {
		StringWriter sw = new StringWriter();
		JAXB.marshal(obj, sw);
		String xmlString = sw.toString();
		return xmlString;
	}

}
