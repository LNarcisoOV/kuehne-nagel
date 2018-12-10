package com.kuehnenagel.util;

import java.io.StringWriter;

import javax.xml.bind.JAXB;

public class UtilConverter {
	public static String convertObjectInXmlString(Object obj) {
		StringWriter sw = new StringWriter();
		JAXB.marshal(obj, sw);
		String xmlString = sw.toString();
		return xmlString;
	}
}
