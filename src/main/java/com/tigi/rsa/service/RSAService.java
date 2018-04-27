package com.tigi.rsa.service;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.tigi.rsa.util.RSAEncryptionUtil;

@Service
public class RSAService {

	public Map<String, String> generatedKey() {
		Map<String, String> result = new HashMap<>();
		
		try {
			System.out.println("********************* START GENERATED KEY *********************");
			if (!RSAEncryptionUtil.isGeneratedKey()) {
				RSAEncryptionUtil.generateKey();
			}
			System.out.println("********************* END GENERATED KEY *********************");
			
			PublicKey publicKey = RSAEncryptionUtil.getPublicKey(); 
			PrivateKey privateKey = RSAEncryptionUtil.getPrivateKey();
			  
			result.put("publicKey", java.util.Base64.getMimeEncoder().encodeToString(publicKey.getEncoded()));
			result.put("privateKey", java.util.Base64.getMimeEncoder().encodeToString(privateKey.getEncoded()));	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String getPublicKeyString() {
		String result = "";
		
		try {
			PublicKey publicKey = RSAEncryptionUtil.getPublicKey(); 
			result = java.util.Base64.getMimeEncoder().encodeToString(publicKey.getEncoded());
		} catch (Exception e) {
			e.printStackTrace();
			result = e.toString();
		}
		
		return result;
	}
	
	
	public String encryptByPublicKey(String plainText) {
		String result = "";
		
		try {
			System.out.println("********************* START ENCRYPT BY PUBLIC KEY *********************");
			
			PublicKey publicKey = RSAEncryptionUtil.getPublicKey();
			final byte[] cipherText = RSAEncryptionUtil.encrypt(plainText, publicKey);
			
			result = java.util.Base64.getMimeEncoder().encodeToString(cipherText);
					
			System.out.println("Original: " + plainText);
		    System.out.println("Encrypted: " + result);
		    
		    System.out.println("********************* END ENCRYPT BY PUBLIC KEY *********************");
		} catch (Exception e) {
			e.printStackTrace();
			result = e.toString();
		}
		
		return result;
	}
	
	public String decryptByPrivateKey(String encryptText) {
		String result = "";
		
		try {
			System.out.println("********************* START DECRYPT BY PRIVATE KEY *********************");
			
			PrivateKey privateKey = RSAEncryptionUtil.getPrivateKey();
			final byte[] cipherText = Base64.decodeBase64(encryptText);
			result = RSAEncryptionUtil.decrypt(cipherText, privateKey);
								
		    System.out.println("Encrypted: " + encryptText);
		    System.out.println("Decrypted: " + result);
		    
		    System.out.println("********************* END DECRYPT BY PRIVATE KEY *********************");
		} catch (Exception e) {
			e.printStackTrace();
			result = e.toString();
		}
		
		return result;
	}
	
	public String encryptWithPublicKeyParam(String plainText, String publicKeyParam) {
		String result = "";
		
		try {
			System.out.println("********************* START ENCRYPT BY PUBLIC KEY PARAM *********************");
			
			byte[] publicBytes = Base64.decodeBase64(publicKeyParam);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
			
			final byte[] cipherText = RSAEncryptionUtil.encrypt(plainText, publicKey);
			
			result = java.util.Base64.getMimeEncoder().encodeToString(cipherText);
					
			System.out.println("Original: " + plainText);
			System.out.println("Encrypted: " + result);
			
			System.out.println("********************* END ENCRYPT BY PUBLIC KEY PARAM *********************");
		} catch (Exception e) {
			e.printStackTrace();
			result = e.toString();
		}
		
		return result;
	}
}
