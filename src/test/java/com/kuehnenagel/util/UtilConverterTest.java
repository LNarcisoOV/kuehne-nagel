package com.kuehnenagel.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.xml.bind.JAXBException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.test.context.SpringBootTest;

import com.kuehnenagel.model.StockLevel;
import com.kuehnenagel.util.UtilConverter;

@SpringBootTest
public class UtilConverterTest {

	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void verifyConversionOfXmlIntoObjectIsNotNull() throws JAXBException {
		StockLevel stockLevel = (StockLevel) UtilConverter.convertXmlStringIntoObjecto(XmlExampleUtil.validXmlExample);
		assertNotNull(stockLevel);
		assertNotNull(stockLevel.getControlSeg()); 
		assertTrue("UC_STOCK_LEVEL".equals(stockLevel.getControlSeg().getTrnnam().toUpperCase()));
	}

	@Test
	public void verifyValuesOfConversionOfXmlIntoObject() throws JAXBException {
		StockLevel stockLevel = (StockLevel) UtilConverter.convertXmlStringIntoObjecto(XmlExampleUtil.validXmlExample);
		assertEquals(stockLevel.getControlSeg().getTrnver(), new Integer(20180100));
		assertEquals(stockLevel.getControlSeg().getUuid(), "0de01919-81eb-4cc7-a51d-15f6085fc1a4");
		assertEquals(stockLevel.getControlSeg().getWhId(), "WHS01");
		assertEquals(stockLevel.getControlSeg().getClientId(), "CLIE01");
		assertEquals(stockLevel.getControlSeg().getIso2CtryName(), "GB");
		assertEquals(stockLevel.getControlSeg().getRequestId(), "bc2a55e8-5a07-4af6-85fd-8290d3ccfb51");
		assertEquals(stockLevel.getControlSeg().getRouteId(), new Integer(186));
	}
	
	@Test
	public void verifyValuesOfConversionOfAnEmptyXmlIntoObject() throws JAXBException {
		StockLevel stockLevel = (StockLevel) UtilConverter.convertXmlStringIntoObjecto(XmlExampleUtil.emptyXmlExample);
		assertEquals(stockLevel.getControlSeg().getTrnver(), new Integer(0));
		assertEquals(stockLevel.getControlSeg().getUuid(), "");
		assertEquals(stockLevel.getControlSeg().getWhId(), "");
		assertEquals(stockLevel.getControlSeg().getClientId(), "");
		assertEquals(stockLevel.getControlSeg().getIso2CtryName(), "");
		assertEquals(stockLevel.getControlSeg().getRequestId(), "");
		assertEquals(stockLevel.getControlSeg().getRouteId(), new Integer(0));
	}
	
	@Test
	public void verifyValuesOfConversionOfAWrongXmlIntoObject() throws JAXBException {
		StockLevel stockLevel = (StockLevel) UtilConverter.convertXmlStringIntoObjecto(XmlExampleUtil.wrongTypesXmlExample);
		assertNull(stockLevel.getControlSeg().getTrnver());
		assertNull(stockLevel.getControlSeg().getRouteId());
	}
	
	@Test(expected = JAXBException.class)
	public void shallThrowsExceptionAfterSendAnInvalidXml() throws JAXBException {
		UtilConverter.convertXmlStringIntoObjecto(XmlExampleUtil.invalidXmlExample);
	}
}
