package com.kuehnenagel.controller;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<StockLevel> sendMessageQueue(@RequestBody StockLevel stockLevel) throws Exception {
		try{
			String xmlString = UtilConverter.convertObjectInXmlString(stockLevel);
			jmsDispatcherInterface.sendMessage("kuehnenagel.queue.sample", xmlString);
			return new ResponseEntity<StockLevel>(HttpStatus.ACCEPTED);
		}catch(JAXBException e) {
			e.printStackTrace();
			return new ResponseEntity<StockLevel>(HttpStatus.NOT_ACCEPTABLE);
		}catch(Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<StockLevel>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ResponseBody
	@PostMapping(value="sendMessageTopic/", produces="application/xml", consumes="application/xml")
	public ResponseEntity<StockLevel> sendMessageTopic(@RequestBody StockLevel stockLevel) {
		try{
			String xmlString = UtilConverter.convertObjectInXmlString(stockLevel);
			jmsDispatcherInterface.sendMessage("kuehnenagel.topic.sample", xmlString);
			return new ResponseEntity<StockLevel>(HttpStatus.ACCEPTED);
		}catch(JAXBException e) {
			e.printStackTrace();
			return new ResponseEntity<StockLevel>(HttpStatus.NOT_ACCEPTABLE);
		}catch(Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<StockLevel>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
