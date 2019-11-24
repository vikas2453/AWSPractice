package com.fun.learning.dynamodbex.model;

import lombok.Data;

@Data
public class Address {
	private String city;
	
	private String street;
	
	private int zip;

}
