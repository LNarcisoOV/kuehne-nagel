package com.kuehnenagel.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.kuehnenagel.model.StockLevel;

public class UtilConverter {
	public static String convertObjectInXmlString(Object obj) {
		StringWriter sw = new StringWriter();
		JAXB.marshal(obj, sw);
		String xmlString = sw.toString();
		return xmlString;
	}

	public static Object convertXmlStringIntoObjecto(String xml) throws JAXBException {
		JAXBContext jaxbContext;
		jaxbContext = JAXBContext.newInstance(StockLevel.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		return (Object) jaxbUnmarshaller.unmarshal(new StringReader(xml));
	}
}
