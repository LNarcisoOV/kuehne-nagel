package com.kuehnenagel.implementation;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.kuehnenagel.interfaces.JmsDispatcherInterface;
import com.kuehnenagel.model.StockLevel;
import com.kuehnenagel.util.UtilConverter;

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
	 	private JmsDispatcherInterface jmsDispatcherInterface;
	 	
	    @JmsListener(destination = "kuehnenagel.queue.sample")
	    public void onReceiverQueue(String xml) {
	    	try{
	    	    StockLevel stockLevel = (StockLevel) UtilConverter.convertXmlStringIntoObjecto(xml);
	    	    publishOnSpecificTopic("kuehnenagel.topic.sample", stockLevel); 
	    	} 	catch (JAXBException e){
	    	    e.printStackTrace();
	    	}
	    }
	    
	    private void publishOnSpecificTopic(String endPoint, StockLevel stockLevel) {
	    	try {
				jmsDispatcherInterface.publishOnSpecificTopic(endPoint, stockLevel);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   	
		}

	    
//		UNCOMMENT IF YOU WANT TO CONSUME THIS TOPIC	    
//		@JmsListener(destination = "kuehnenagel.topic.sample")
//	    public void onReceiverTopic(String str) {
//	    	try{
//	    	    StockLevel stockLevel = (StockLevel) UtilConverter.convertXmlStringIntoObjecto(xml);
//	    	    stockLevel.toString(); 
//	    	} 	catch (JAXBException e){
//	    	    e.printStackTrace();
//	    	}
//	    }

	    @Override
	    public void run(ApplicationArguments args) throws Exception {
	    	jmsDispatcherInterface.publishXMLOnSpecificQueue("kuehnenagel.queue.sample", xmlExample);
//	        jmsTemplateTopic.convertAndSend("kuehnenagel.topic.sample", xmlExample);
	    }
}
