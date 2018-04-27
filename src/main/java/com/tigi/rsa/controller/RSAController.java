package com.tigi.rsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tigi.rsa.service.RSAService;

@RestController
public class RSAController {
	
	@Autowired
	RSAService rsaService;

	@RequestMapping("/generateKey")
	public String generateKey() {
		return rsaService.generatedKey().toString();
	}
	
	@RequestMapping("/getPublicKey")
	public String getPublicKey() {
		return rsaService.getPublicKeyString();
	}
	
	@RequestMapping(path = "/encryptByPublicKey", method = {RequestMethod.POST})
	public String encryptByPublicKey(@RequestParam(value = "plainText") String plainText) {
		return rsaService.encryptByPublicKey(plainText);
	}
	
	@RequestMapping(path = "/decryptByPrivateKey", method = {RequestMethod.POST})
	public String decryptByPrivateKey(@RequestParam(value = "encryptText") String encryptText) {
		return rsaService.decryptByPrivateKey(encryptText);
	}
	
	@RequestMapping(path = "/encryptWithPublicKeyParam", method = {RequestMethod.POST})
	public String encryptWithPublicKeyParam(@RequestParam(value = "plainText") String plainText, @RequestParam(value = "publicKey") String publicKey) {
		return rsaService.encryptWithPublicKeyParam(plainText, publicKey);
	}
}
