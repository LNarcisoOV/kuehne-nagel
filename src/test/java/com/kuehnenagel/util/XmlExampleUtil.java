package com.kuehnenagel.util;

public class XmlExampleUtil {
	
	public static final String validXmlExample = "<UC_STOCK_LEVEL_IFD>\r\n" + "<CTRL_SEG>\r\n"
			+ "<TRNNAM>UC_STOCK_LEVEL</TRNNAM>\r\n" + "<TRNVER>20180100</TRNVER>\r\n"
			+ "<UUID>0de01919-81eb-4cc7-a51d-15f6085fc1a4</UUID>\r\n" + "<WH_ID>WHS01</WH_ID>\r\n"
			+ "<CLIENT_ID>CLIE01</CLIENT_ID>\r\n" + "<ISO_2_CTRY_NAME>GB</ISO_2_CTRY_NAME>\r\n"
			+ "<REQUEST_ID>bc2a55e8-5a07-4af6-85fd-8290d3ccfb51</REQUEST_ID>\r\n" + "<ROUTE_ID>186</ROUTE_ID>\r\n"
			+ "</CTRL_SEG>\r\n" + "</UC_STOCK_LEVEL_IFD>";
	
	//two strings in interger fields, 'abcd' and 'efgh'
	public static final String wrongTypesXmlExample = "<UC_STOCK_LEVEL_IFD>\r\n" + "<CTRL_SEG>\r\n"
			+ "<TRNNAM>UC_STOCK_LEVEL</TRNNAM>\r\n" + "<TRNVER>abcd</TRNVER>\r\n"
			+ "<UUID>0de01919-81eb-4cc7-a51d-15f6085fc1a4</UUID>\r\n" + "<WH_ID>WHS01</WH_ID>\r\n"
			+ "<CLIENT_ID>CLIE01</CLIENT_ID>\r\n" + "<ISO_2_CTRY_NAME>GB</ISO_2_CTRY_NAME>\r\n"
			+ "<REQUEST_ID>bc2a55e8-5a07-4af6-85fd-8290d3ccfb51</REQUEST_ID>\r\n" + "<ROUTE_ID>efgh</ROUTE_ID>\r\n"
			+ "</CTRL_SEG>\r\n" + "</UC_STOCK_LEVEL_IFD>";
	
	public static final String emptyXmlExample = "<UC_STOCK_LEVEL_IFD>\r\n" + "<CTRL_SEG>\r\n"
			+ "<TRNNAM></TRNNAM>\r\n" + "<TRNVER></TRNVER>\r\n"
			+ "<UUID></UUID>\r\n" + "<WH_ID></WH_ID>\r\n"
			+ "<CLIENT_ID></CLIENT_ID>\r\n" + "<ISO_2_CTRY_NAME></ISO_2_CTRY_NAME>\r\n"
			+ "<REQUEST_ID></REQUEST_ID>\r\n" + "<ROUTE_ID></ROUTE_ID>\r\n"
			+ "</CTRL_SEG>\r\n" + "</UC_STOCK_LEVEL_IFD>";

	//Xml without first tag <UC_STOCK_LEVEL_IFD>\r\n
	public static final String invalidXmlExample = "" + "<CTRL_SEG>\r\n"
			+ "<TRNNAM>UC_STOCK_LEVEL</TRNNAM>\r\n" + "<TRNVER>20180100</TRNVER>\r\n"
			+ "<UUID>0de01919-81eb-4cc7-a51d-15f6085fc1a4</UUID>\r\n" + "<WH_ID>WHS01</WH_ID>\r\n"
			+ "<CLIENT_ID>CLIE01</CLIENT_ID>\r\n" + "<ISO_2_CTRY_NAME>GB</ISO_2_CTRY_NAME>\r\n"
			+ "<REQUEST_ID>bc2a55e8-5a07-4af6-85fd-8290d3ccfb51</REQUEST_ID>\r\n" + "<ROUTE_ID>186</ROUTE_ID>\r\n"
			+ "</CTRL_SEG>\r\n" + "</UC_STOCK_LEVEL_IFD>";

}
