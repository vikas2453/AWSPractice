package com.fun.learning.dynamodbex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.fun.learning.dynamodbex.config.Log;



@Service
public class SQSService {

	AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

	@Log
	public CreateQueueResult createQueue(String queueName, int delaySec, int messageRetentionPerid,
			int visibilityTimeout) {
	
		CreateQueueRequest createRequest = new CreateQueueRequest(queueName)
				.addAttributesEntry("DelaySeconds", String.valueOf(delaySec))
				.addAttributesEntry("MessageRetentionPeriod", String.valueOf(messageRetentionPerid))
				.addAttributesEntry("VisibilityTimeout", String.valueOf(visibilityTimeout));

		CreateQueueResult createQueueResult = sqs.createQueue(createRequest);		
		return createQueueResult;
	}
	
	@Log
	public SendMessageResult  sendMessage(String queueURL, String message ) {
		return sqs.sendMessage(queueURL, message);
	}
	
	@Log
	public  List<Message> readMessage(String queueURL ) {
		ReceiveMessageResult receiveMessageResult = sqs.receiveMessage(queueURL);
		return receiveMessageResult.getMessages();
	}
}
