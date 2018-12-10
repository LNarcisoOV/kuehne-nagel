package com.kuehnenagel.Util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.test.context.SpringBootTest;

import com.kuehnenagel.model.StockLevel;

@SpringBootTest
public class UtilConverterTest {

	private static final String validXmlExample = "<UC_STOCK_LEVEL_IFD>\r\n" + "<CTRL_SEG>\r\n"
			+ "<TRNNAM>UC_STOCK_LEVEL</TRNNAM>\r\n" + "<TRNVER>20180100</TRNVER>\r\n"
			+ "<UUID>0de01919-81eb-4cc7-a51d-15f6085fc1a4</UUID>\r\n" + "<WH_ID>WHS01</WH_ID>\r\n"
			+ "<CLIENT_ID>CLIE01</CLIENT_ID>\r\n" + "<ISO_2_CTRY_NAME>GB</ISO_2_CTRY_NAME>\r\n"
			+ "<REQUEST_ID>bc2a55e8-5a07-4af6-85fd-8290d3ccfb51</REQUEST_ID>\r\n" + "<ROUTE_ID>186</ROUTE_ID>\r\n"
			+ "</CTRL_SEG>\r\n" + "</UC_STOCK_LEVEL_IFD>";
	
	//two strings in interger fields, 'abcd' and 'efgh'
	private static final String wrongTypesXmlExample = "<UC_STOCK_LEVEL_IFD>\r\n" + "<CTRL_SEG>\r\n"
			+ "<TRNNAM>UC_STOCK_LEVEL</TRNNAM>\r\n" + "<TRNVER>abcd</TRNVER>\r\n"
			+ "<UUID>0de01919-81eb-4cc7-a51d-15f6085fc1a4</UUID>\r\n" + "<WH_ID>WHS01</WH_ID>\r\n"
			+ "<CLIENT_ID>CLIE01</CLIENT_ID>\r\n" + "<ISO_2_CTRY_NAME>GB</ISO_2_CTRY_NAME>\r\n"
			+ "<REQUEST_ID>bc2a55e8-5a07-4af6-85fd-8290d3ccfb51</REQUEST_ID>\r\n" + "<ROUTE_ID>efgh</ROUTE_ID>\r\n"
			+ "</CTRL_SEG>\r\n" + "</UC_STOCK_LEVEL_IFD>";
	
	private static final String emptyXmlExample = "<UC_STOCK_LEVEL_IFD>\r\n" + "<CTRL_SEG>\r\n"
			+ "<TRNNAM></TRNNAM>\r\n" + "<TRNVER></TRNVER>\r\n"
			+ "<UUID></UUID>\r\n" + "<WH_ID></WH_ID>\r\n"
			+ "<CLIENT_ID></CLIENT_ID>\r\n" + "<ISO_2_CTRY_NAME></ISO_2_CTRY_NAME>\r\n"
			+ "<REQUEST_ID></REQUEST_ID>\r\n" + "<ROUTE_ID></ROUTE_ID>\r\n"
			+ "</CTRL_SEG>\r\n" + "</UC_STOCK_LEVEL_IFD>";

	//Xml without first tag <UC_STOCK_LEVEL_IFD>\r\n
	private static final String invalidXmlExample = "" + "<CTRL_SEG>\r\n"
			+ "<TRNNAM>UC_STOCK_LEVEL</TRNNAM>\r\n" + "<TRNVER>20180100</TRNVER>\r\n"
			+ "<UUID>0de01919-81eb-4cc7-a51d-15f6085fc1a4</UUID>\r\n" + "<WH_ID>WHS01</WH_ID>\r\n"
			+ "<CLIENT_ID>CLIE01</CLIENT_ID>\r\n" + "<ISO_2_CTRY_NAME>GB</ISO_2_CTRY_NAME>\r\n"
			+ "<REQUEST_ID>bc2a55e8-5a07-4af6-85fd-8290d3ccfb51</REQUEST_ID>\r\n" + "<ROUTE_ID>186</ROUTE_ID>\r\n"
			+ "</CTRL_SEG>\r\n" + "</UC_STOCK_LEVEL_IFD>";

	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void verifyConversionOfXmlIntoObjectIsNotNull() {
		StockLevel stockLevel = convertXmlIntoStockLevel(validXmlExample);
		assertTrue(stockLevel != null);
	}

	@Test
	public void verifyValuesOfConversionOfXmlIntoObject() {
		StockLevel stockLevel = convertXmlIntoStockLevel(validXmlExample);
		assertEquals(stockLevel.getControlSeg().getTrnver(), new Integer(20180100));
		assertEquals(stockLevel.getControlSeg().getUuid(), "0de01919-81eb-4cc7-a51d-15f6085fc1a4");
		assertEquals(stockLevel.getControlSeg().getWhId(), "WHS01");
		assertEquals(stockLevel.getControlSeg().getClientId(), "CLIE01");
		assertEquals(stockLevel.getControlSeg().getIso2CtryName(), "GB");
		assertEquals(stockLevel.getControlSeg().getRequestId(), "bc2a55e8-5a07-4af6-85fd-8290d3ccfb51");
		assertEquals(stockLevel.getControlSeg().getRouteId(), new Integer(186));
	}
	
	@Test
	public void verifyValuesOfConversionOfAnEmptyXmlIntoObject() {
		StockLevel stockLevel = convertXmlIntoStockLevel(emptyXmlExample);
		assertEquals(stockLevel.getControlSeg().getTrnver(), new Integer(0));
		assertEquals(stockLevel.getControlSeg().getUuid(), "");
		assertEquals(stockLevel.getControlSeg().getWhId(), "");
		assertEquals(stockLevel.getControlSeg().getClientId(), "");
		assertEquals(stockLevel.getControlSeg().getIso2CtryName(), "");
		assertEquals(stockLevel.getControlSeg().getRequestId(), "");
		assertEquals(stockLevel.getControlSeg().getRouteId(), new Integer(0));
	}
	
	@Test
	public void verifyValuesOfConversionOfAWrongXmlIntoObject() {
		StockLevel stockLevel = convertXmlIntoStockLevel(wrongTypesXmlExample);
		assertTrue(stockLevel.getControlSeg().getTrnver() == null);
		assertTrue(stockLevel.getControlSeg().getRouteId() == null);
	}
	
	@Test
	public void shallThrowsExceptionAfterSendAnInvalidXml() {
		try {
			convertXmlIntoStockLevel(invalidXmlExample);
		}catch(Exception e) {
			 assertThat(e)
			 .isInstanceOf(AssertionError.class)
			 .hasMessage("A marcação no documento após o elemento-raiz deve estar correta.");
			 //Translating: The markup in document after root-element shall be correct.
		}
	}
	
	private StockLevel convertXmlIntoStockLevel(String xml) {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(StockLevel.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			return (StockLevel) jaxbUnmarshaller.unmarshal(new StringReader(xml));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

}
