package com.fun.learning.dynamodbex.service;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fun.learning.dynamodbex.BaseTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KMSServiceTest extends BaseTest {

	@Autowired
	private KMSService kmsService;

	// s3MasterKeyArn is AWS managed master key and it can't be used by user to
	// decrypt the data, it is only used by AWS as default key to encrypt the S3 boject when no other key is provided and also 
	// to encrypt the datakey
	String s3MasterKeyArn = "arn:aws:kms:us-east-2:939244464803:key/30d3a5db-e6d4-4d3b-bcd7-22140b9c78c2";
	String myKeyArn = "arn:aws:kms:us-east-2:939244464803:key/89465ee7-39eb-449d-9ef5-6384eac38e06";

	@Test
	public void testEncrypt() {
		String encryptedText = kmsService.encrypt("This is my plain Text by Vikas Chaudhary", myKeyArn);
		log.debug("encryptedText:-> " + encryptedText);
	}
	
	@Test
	public void testEncryptFile() throws IOException {
		File file = new File("plainFile.txt");
		kmsService.encrypt(file, myKeyArn);
		
	}

}
