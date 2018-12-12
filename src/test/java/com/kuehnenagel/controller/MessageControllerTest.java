package com.kuehnenagel.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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

import com.kuehnenagel.Util.XmlExampleUtil;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageControllerTest {
	
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
										.content(XmlExampleUtil.validXmlExample);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertTrue(response.getContentAsString().contains("CLIE01"));
	}
	
	@Test
	public void testSendMessageQueueWithInvalidXml() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/sendMessageQueue/")
										.accept(MediaType.APPLICATION_XML)
										.contentType(MediaType.APPLICATION_XML)
										.content(XmlExampleUtil.invalidXmlExample);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}
	
	@Test
	public void testSendMessageQueueWithEmptyXml() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/sendMessageQueue/")
										.accept(MediaType.APPLICATION_XML)
										.contentType(MediaType.APPLICATION_XML)
										.content(XmlExampleUtil.emptyXmlExample);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertTrue(response.getContentAsString().contains("<CLIENT_ID></CLIENT_ID>"));
	}
}
