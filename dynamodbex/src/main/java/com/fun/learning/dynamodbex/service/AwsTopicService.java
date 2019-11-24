package com.fun.learning.dynamodbex.service;

import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SubscribeResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AwsTopicService {

	public String createTopic(String topic) {
		final CreateTopicRequest createTopicRequest = new CreateTopicRequest(topic);
		final CreateTopicResult createTopicResult = AmazonSNSClientBuilder.defaultClient()
				.createTopic(createTopicRequest);
		String topicUrn = createTopicResult.getTopicArn();

		log.info("topicArn:-> " + topicUrn);
		return topicUrn;
	}

	public String subscribeToTopic(String topicArn, String protocol, String endPoint) {

		SubscribeResult subscribeResult = AmazonSNSClientBuilder.defaultClient().subscribe(topicArn, protocol,
				endPoint);

		log.info("SubscriptionArn:-> " + subscribeResult);
		return subscribeResult.toString();
	}
	
	public String publishToTopic(String topicArn, String message) {

		 PublishResult publishResult= AmazonSNSClientBuilder.defaultClient().publish(topicArn, message);
				

		log.info("publishResult:-> " + publishResult);
		return publishResult.toString();
	}


}
