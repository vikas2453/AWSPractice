package com.fun.learning;

import java.util.Map;
import java.util.logging.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class RequestFunctionalHandler implements RequestHandler<Map<String, String>, String> {

	private static Logger logger= Logger.getLogger(RequestFunctionalHandler.class.getName());
	public String handleRequest(Map<String, String> input, Context context) {
		logger.info("my First lambda function invoked");
		return "my First lambda function";
	}

}
