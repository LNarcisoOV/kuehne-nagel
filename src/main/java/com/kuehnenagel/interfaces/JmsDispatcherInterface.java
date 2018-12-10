package com.kuehnenagel.interfaces;

import com.kuehnenagel.model.StockLevel;

public interface JmsDispatcherInterface {
	public void sendMessage(String endPoint, String xml) throws Exception;
	public void publishOnSpecificTopic(String endPoint, StockLevel stockLevel) throws Exception;
	public void publishOnSpecificQueue(String endPoint, StockLevel stockLevel) throws Exception;
	public void publishXMLOnSpecificQueue(String endPoint, String xml) throws Exception;
}
