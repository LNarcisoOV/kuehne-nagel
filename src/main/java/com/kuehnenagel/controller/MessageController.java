package com.kuehnenagel.controller;

import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kuehnenagel.interfaces.JmsDispatcherInterface;
import com.kuehnenagel.model.StockLevel;
import com.kuehnenagel.util.UtilConverter;

@Controller
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
	public StockLevel sendMessageQueue(@RequestBody String xml){
		StockLevel stockLevel = new StockLevel();
		try{
			stockLevel = (StockLevel) UtilConverter.convertXmlStringIntoObjecto(xml);
			jmsDispatcherInterface.publishOnSpecificQueue("kuehnenagel.queue.sample", stockLevel);
			stockLevel.setResponseStatusCode(HttpStatus.OK.value());
			return stockLevel;
		}catch(UnmarshalException h) {
			h.printStackTrace();
			stockLevel.setResponseStatusCode(HttpStatus.BAD_REQUEST.value());
			stockLevel.setResponseMessage("INVALID XML.");
			return stockLevel;
		}catch(JAXBException e) {
			e.printStackTrace();
			stockLevel.setResponseStatusCode(HttpStatus.BAD_REQUEST.value());
			stockLevel.setResponseMessage("INVALID XML.");
			return stockLevel;
		}catch(Exception ex) {
			ex.printStackTrace();
			stockLevel.setResponseStatusCode(HttpStatus.BAD_REQUEST.value());
			stockLevel.setResponseMessage("AN ERROR HAPPENS.");
			return stockLevel;
		}
	}
	
	@ResponseBody
	@PostMapping(value="sendMessageQueueTest/", produces="application/xml", consumes="application/xml")
	public StockLevel sendMessageQueueTest(@RequestBody String xml){
		StockLevel stockLevel = new StockLevel();
		try{
			stockLevel = (StockLevel) UtilConverter.convertXmlStringIntoObjecto(xml);
			jmsDispatcherInterface.publishOnSpecificQueue("kuehnenagel.queue.sample.testController", stockLevel);
			stockLevel.setResponseStatusCode(HttpStatus.OK.value());
			return stockLevel;
		}catch(UnmarshalException h) {
			stockLevel.setResponseStatusCode(HttpStatus.BAD_REQUEST.value());
			stockLevel.setResponseMessage("INVALID XML.");
			return stockLevel;
		}catch(JAXBException e) {
			stockLevel.setResponseStatusCode(HttpStatus.BAD_REQUEST.value());
			stockLevel.setResponseMessage("INVALID XML.");
			return stockLevel;
		}catch(Exception ex) {
			stockLevel.setResponseStatusCode(HttpStatus.BAD_REQUEST.value());
			stockLevel.setResponseMessage("AN ERROR HAPPENS.");
			return stockLevel;
		}
	}
}
