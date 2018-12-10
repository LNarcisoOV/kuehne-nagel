package com.kuehnenagel.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.kuehnenagel.interfaces.JmsDispatcherInterface;
import com.kuehnenagel.model.StockLevel;
import com.kuehnenagel.util.UtilConverter;

@Service
public class JmsDispatcher implements ApplicationRunner, JmsDispatcherInterface{

	@Autowired 
 	private JmsTemplate jmsTemplate;
 	
	public void sendMessage(String endPoint, String xml) throws Exception {
        jmsTemplate.convertAndSend(endPoint, xml);
    }
	
	public void publishOnSpecificTopic(String endPoint, StockLevel stockLevel) throws Exception {
		String xml = UtilConverter.convertObjectInXmlString(stockLevel);
		sendMessage(endPoint, xml);
    }

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
