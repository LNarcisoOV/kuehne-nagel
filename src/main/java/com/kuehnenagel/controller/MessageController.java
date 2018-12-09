package com.kuehnenagel.controller;

import java.io.StringWriter;

import javax.xml.bind.JAXB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.kuehnenagel.model.StockLevel;

@RestController
@RequestMapping("/")
public class MessageController {
	
	@Autowired
	private JmsTemplate jmsTemplate;

	@GetMapping
	public ModelAndView initialPage() {
		ModelAndView modelAndView = new ModelAndView("html/login/loginForm");
		return modelAndView;
	}

	@ResponseBody
	@PostMapping(value="sendMessage/", produces="application/xml", consumes="application/xml")
	public StockLevel processXml(@RequestBody StockLevel xml) {
		try{
			String xmlString = convertObjectInXmlString(xml);
			jmsTemplate.convertAndSend("queue.sample", xmlString);
		}catch(Exception e) {
			
		}finally {
			return xml;
		}
	}
	
	public String convertObjectInXmlString(Object obj) {
		StringWriter sw = new StringWriter();
		JAXB.marshal(obj, sw);
		String xmlString = sw.toString();
		return xmlString;
	}

}
