package com.kuehnenagel.interfaces;

public interface JmsDispatcherInterface {
	public void sendMessage(String endPoint, String xml) throws Exception;
}
