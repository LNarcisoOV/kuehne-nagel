package com.kuehnenagel.implementation;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.kuehnenagel.model.StockLevel;

@Component
public class JmsListenerComponent implements ApplicationRunner{
	
		private static final String xmlExample = "<UC_STOCK_LEVEL_IFD>\r\n" + 
				"<CTRL_SEG>\r\n" + 
				"<TRNNAM>UC_STOCK_LEVEL</TRNNAM>\r\n" + 
				"<TRNVER>20180100</TRNVER>\r\n" + 
				"<UUID>0de01919-81eb-4cc7-a51d-15f6085fc1a4</UUID>\r\n" + 
				"<WH_ID>WHS01</WH_ID>\r\n" + 
				"<CLIENT_ID>CLIE01</CLIENT_ID>\r\n" + 
				"<ISO_2_CTRY_NAME>GB</ISO_2_CTRY_NAME>\r\n" + 
				"<REQUEST_ID>bc2a55e8-5a07-4af6-85fd-8290d3ccfb51</REQUEST_ID>\r\n" + 
				"<ROUTE_ID>186</ROUTE_ID>\r\n" + 
				"</CTRL_SEG>\r\n" + 
				"</UC_STOCK_LEVEL_IFD>";

	 	@Autowired 
	 	private JmsTemplate jmsTemplate;
	 	
	 	@Autowired 
	 	private JmsTemplate jmsTemplateTopic;
	 	
	    @JmsListener(destination = "queue.sample")
	    public void onReceiverQueue(String str) {
	    	JAXBContext jaxbContext;
	    	try{
	    	    jaxbContext = JAXBContext.newInstance(StockLevel.class);             
	    	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    	    StockLevel stockLevel = (StockLevel) jaxbUnmarshaller.unmarshal(new StringReader(str));
	    	     
	    	    System.out.println(stockLevel);
	    	} 	catch (JAXBException e){
	    	    e.printStackTrace();
	    	}
	    }
	    
	    @JmsListener(destination = "topic.sample", containerFactory = "jmsFactoryTopic")
	    public void onReceiverTopic(String str) {
	    	JAXBContext jaxbContext;
	    	try{
	    	    jaxbContext = JAXBContext.newInstance(StockLevel.class);             
	    	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    	    StockLevel stockLevel = (StockLevel) jaxbUnmarshaller.unmarshal(new StringReader(str));
	    	     
	    	    System.out.println(stockLevel);
	    	} 	catch (JAXBException e){
	    	    e.printStackTrace();
	    	}
	    }

	    @Override
	    public void run(ApplicationArguments args) throws Exception {
	        jmsTemplate.convertAndSend("queue.sample", xmlExample);
	        jmsTemplateTopic.convertAndSend("topic.sample", xmlExample);
	    }
}
