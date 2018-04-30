package com.tigi.rsa;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

public class RSAControllerIntegrationTest extends AbstractWebIntegrationTest {
	
	static final String PLAIN_TEXT      = "test plain text";
	static final String TEST_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhHOx/thUgKvWp5/cyE6HXbfYBqSVDArBA/NCmY6SsrdWcAc1aXUh2Ho/H3jpQSvkqfqGrfykcNP4Z/WjsLMp3Iyw+dXnOuIZbezuAuB0DIZLMLGlu42HPKQIrE+AaxF7ISLVQrc9LVjzjjNtj+SeYKxP+3+1DNk4jBTP1IraN//zaxND2Kz3iQUJszIXZNeVPG+mUqBqxsJBV0Ejp7BqaziCpnF/4CqSC11+D6Inm0ItwUa5lQZhViSr4689fhYt3Hy7LSsBAJ4Rcv+4HQRM9R4IIE1KBTYUhHTO7SSM8U06PyAwWfUJ9WVoPYqtHgP4BludRk6iTS2Yzwo9FeeWbQIDAQAB";
	
	
	@Test
    public void generateKey() throws Exception {
        ResponseEntity<String> response = get("/generateKey", String.class);
        
        assertNotNull(response.getBody());
    }
	
	@Test
    public void getPublicKey() throws Exception {
        ResponseEntity<String> response = get("/getPublicKey", String.class);
        
        assertNotNull(response.getBody());
    }
	
	@Test
    public void testRSA() throws Exception {
        Map<String, String> param = new HashMap<String, String>();
        param.put("plainText", PLAIN_TEXT);
        
        ResponseEntity<String> response = post("/encryptByPublicKey", param, String.class);
        assertNotNull(response.getBody());
        
        String encryptText = response.getBody();
        param = new HashMap<String, String>();
        param.put("encryptText", encryptText);
        
        response = post("/decryptByPrivateKey", param, String.class);
        assertNotNull(response.getBody());
        
        String decryptText = response.getBody();
        assertThat(decryptText, equalTo(PLAIN_TEXT));
    }
	
	@Test
    public void encryptWithPublicKeyParam() throws Exception {		
        Map<String, String> param = new HashMap<String, String>();
        param.put("plainText", PLAIN_TEXT);
        param.put("publicKey", TEST_PUBLIC_KEY);
        
		ResponseEntity<String> response = post("/encryptWithPublicKeyParam", param, String.class);
        
        assertNotNull(response.getBody());
    }
}
