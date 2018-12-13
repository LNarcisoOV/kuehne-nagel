package com.kuehnenagel.implementation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JmsListenerComponentTest {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Before
	public void init() {
		this.jmsTemplate.convertAndSend("kuehnenagel.queue.sample.testJMSComponent", "JmsMessageForJUnitTest");
	}

	@Test
	public void verifyIfMessageWasReceived() {
        assertThat(this.jmsTemplate.receiveAndConvert("kuehnenagel.queue.sample.testJMSComponent"))
        .isEqualTo("JmsMessageForJUnitTest");
	}
}
