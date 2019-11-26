package com.fun.learning.dynamodbex.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fun.learning.dynamodbex.BaseTest;

public class SQSServiceTest extends BaseTest {
	
	@Autowired
	private SQSService sqsService;
	
	private String myTestQueueURL= "https://sqs.us-east-1.amazonaws.com/939244464803/myTestQueu";
	
	@Test
	public void testCreateQueue() {
		sqsService.createQueue("myTestQueu", 20, 300, 20);
	}
	
	@Test
	public void testSendMessage() {
		sqsService.sendMessage(myTestQueueURL, "Hello!! how are you?");
	}
	
	@Test
	public void testReadMessage() {
		sqsService.readMessage(myTestQueueURL);
	}

}
