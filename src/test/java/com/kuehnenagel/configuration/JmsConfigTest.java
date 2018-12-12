package com.kuehnenagel.configuration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JmsConfigTest {

	@Autowired
	private JmsConfig jmsConfig;
	
	@Test
	public void testConnectionFactory() {
		assertNotNull(jmsConfig.connectionFactory());
	}
	
	@Test
	public void testUrlConnectionFactory() {
		assertTrue("tcp://localhost:61616".equals(jmsConfig.connectionFactory().getBrokerURL()));
	}
}
