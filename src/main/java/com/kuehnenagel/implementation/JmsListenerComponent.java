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

	    @Override
	    public void run(ApplicationArguments args) throws Exception {
	    }
}
