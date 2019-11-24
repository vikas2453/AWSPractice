package com.fun.learning.dynamodbex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fun.learning.dynamodbex.model.Observation;
import com.fun.learning.dynamodbex.service.SQSService;

@RestController
@RequestMapping("/sqs")

public class SQSController {
	
	@Autowired
	private SQSService sqsService;
	
	private String myTestQueueURL= "https://sqs.us-east-1.amazonaws.com/939244464803/myTestQueu";
	
	
	@PostMapping("/create")
	public Observation createObservation(@RequestBody Observation obs) {
		
		sqsService.sendMessage(myTestQueueURL, "Hello!! how are you?");
		
		
		
		return obs;
	}
}
