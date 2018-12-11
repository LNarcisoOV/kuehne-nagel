package com.kuehnenagel.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageControllerTest {
	
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
		
	private MockMvc mockMvc;
	
    private WebApplicationContext wac;
	
	@Autowired
	private MessageController messageController;
	
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();
	}
	
	@Test
	public void testTheContextOfTheApplication() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isOk());
	}

	@Test
	public void testSendMessageQueue() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/sendMessageQueue/")
										.accept(MediaType.APPLICATION_XML)
										.contentType(MediaType.APPLICATION_XML)
										.content(validXmlExample);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.ACCEPTED.value(), response.getStatus());

	}
}
