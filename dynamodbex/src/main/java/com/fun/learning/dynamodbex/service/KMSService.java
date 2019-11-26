package com.fun.learning.dynamodbex.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import com.amazonaws.encryptionsdk.AwsCrypto;
import com.amazonaws.encryptionsdk.CryptoInputStream;
import com.amazonaws.encryptionsdk.CryptoResult;
import com.amazonaws.encryptionsdk.jce.JceMasterKey;
import com.amazonaws.encryptionsdk.kms.KmsMasterKey;
import com.amazonaws.encryptionsdk.kms.KmsMasterKeyProvider;
import com.amazonaws.util.IOUtils;

@Service
public class KMSService {

	final AwsCrypto crypto = new AwsCrypto();
	private KmsMasterKeyProvider prov;

	public void createKey() {
		SecretKey cryptoKey = retrieveEncryptionKey();
		JceMasterKey masterKey = JceMasterKey.getInstance(cryptoKey, "Example", "RandomKey", "AES/GCM/NoPadding");

	}

	private static SecretKey retrieveEncryptionKey() {
		SecureRandom rnd = new SecureRandom();
		byte[] rawKey = new byte[16]; // 128 bits
		rnd.nextBytes(rawKey);
		return new SecretKeySpec(rawKey, "AES");
	}

	public String encrypt(String plainText, String keyArn) {
		prov = KmsMasterKeyProvider.builder().withKeysForEncryption(keyArn).build();
		Map<String, String> encryptionContext = Collections.singletonMap("ExampleContextKey", "ExampleContextValue");
		CryptoResult<byte[], KmsMasterKey> decryptResult = crypto.encryptData(prov, plainText.getBytes(),encryptionContext );
		byte[] resutByte = decryptResult.getResult();
		return resutByte.toString();
	}

	public void encrypt(File file, String keyArn) throws IOException {
		SecretKey cryptoKey = retrieveEncryptionKey();

		JceMasterKey masterKey = JceMasterKey.getInstance(cryptoKey, "Example", "RandomKey", "AES/GCM/NoPadding");

		AwsCrypto crypto = new AwsCrypto();

		Map<String, String> context = Collections.singletonMap("Example", "FileStreaming");

		FileInputStream in = new FileInputStream(file);

		CryptoInputStream<JceMasterKey> encryptingStream = crypto.createEncryptingStream(masterKey, in, context);

		FileOutputStream out = new FileOutputStream(file + ".encrypted");
		IOUtils.copy(encryptingStream, out);
		encryptingStream.close();
		out.close();

		in = new FileInputStream(file + ".encrypted");
		CryptoInputStream<JceMasterKey> decryptingStream = crypto.createDecryptingStream(masterKey, in);
		// Does it contain the expected encryption context?
		if (!"FileStreaming".equals(decryptingStream.getCryptoResult().getEncryptionContext().get("Example"))) {
			throw new IllegalStateException("Bad encryption context");
		}

		// Return the plaintext data
		out = new FileOutputStream(file + ".decrypted");
		IOUtils.copy(decryptingStream, out);
		decryptingStream.close();
		out.close();

	}
}
