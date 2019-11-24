package com.fun.learning.dynamodbex.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fun.learning.dynamodbex.BaseTest;

public class AwsSNSServiceTest extends BaseTest {

	@Autowired
	private AwsTopicService awsSNSService;
	
	

	@Test
	public void testCreateTopic() {	
		
		String topicurn=awsSNSService.createTopic("TestTopic");
		assertThat(topicurn, is(notNullValue()));
	}
	
	@Test
	public void testSubscribeToTopic() {	
		
		String subscriptionResult=awsSNSService.subscribeToTopic("arn:aws:sns:us-east-1:939244464803:TestTopic", "SMS", "7044501544");
		assertThat(subscriptionResult, is(notNullValue()));
	}
	
	@Test
	public void testPublishToTopic() {	
		
		String pubslishResult=awsSNSService.publishToTopic("arn:aws:sns:us-east-1:939244464803:TestTopic", "everything is fine");
		assertThat(pubslishResult, is(notNullValue()));
	}

}
