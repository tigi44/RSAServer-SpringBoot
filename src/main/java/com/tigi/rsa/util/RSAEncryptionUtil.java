package com.tigi.rsa.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;


public class RSAEncryptionUtil {

  public static final String ALGORITHM = "RSA";
  public static final int KEY_SIZE = 2048;

  public static final String PRIVATE_KEY_FILE = "src/keys/private.key";
  public static final String PUBLIC_KEY_FILE = "src/keys/public.key";
  
  public static void generateKey() {
    try {
    		final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
    		keyGen.initialize(KEY_SIZE);
    		final KeyPair keyPair = keyGen.generateKeyPair();

    		File privateKeyFile = new File(PRIVATE_KEY_FILE);
    		File publicKeyFile = new File(PUBLIC_KEY_FILE);

    		if (privateKeyFile.getParentFile() != null) {
    			privateKeyFile.getParentFile().mkdirs();
    		}
    		privateKeyFile.createNewFile();
      
    		if (publicKeyFile.getParentFile() != null) {
    			publicKeyFile.getParentFile().mkdirs();
    		}
    		publicKeyFile.createNewFile();

    		ObjectOutputStream publicKeyOS = new ObjectOutputStream(new FileOutputStream(publicKeyFile));
    		publicKeyOS.writeObject(keyPair.getPublic());
    		publicKeyOS.close();

    		ObjectOutputStream privateKeyOS = new ObjectOutputStream(new FileOutputStream(privateKeyFile));
    		privateKeyOS.writeObject(keyPair.getPrivate());
    		privateKeyOS.close();
      
      
    		System.out.println ("-----BEGIN PUBLIC KEY-----");
	    System.out.println (java.util.Base64.getMimeEncoder().encodeToString(keyPair.getPublic().getEncoded()));
	    System.out.println ("-----END PUBLIC KEY-----");
	    System.out.println ("-----BEGIN PRIVATE KEY-----");
	    System.out.println (java.util.Base64.getMimeEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
	    System.out.println ("-----END PRIVATE KEY-----");
    } catch (Exception e) {
    		e.printStackTrace();
    }
  }

  public static boolean isGeneratedKey() {
    File privateKey = new File(PRIVATE_KEY_FILE);
    File publicKey = new File(PUBLIC_KEY_FILE);

    if (privateKey.exists() && publicKey.exists()) {
    		return true;
    } else {
    		return false;
    }
  }

  public static byte[] encrypt(String text, PublicKey key) {
    byte[] cipherText = null;
    try {
    		final Cipher cipher = Cipher.getInstance(ALGORITHM);
    		cipher.init(Cipher.ENCRYPT_MODE, key);
    		cipherText = cipher.doFinal(text.getBytes());
    } catch (Exception e) {
    		e.printStackTrace();
    }
    
    return cipherText;
  }

  public static String decrypt(byte[] text, PrivateKey key) {
    byte[] dectyptedText = null;
    try {        
    		final Cipher cipher = Cipher.getInstance(ALGORITHM);
    		cipher.init(Cipher.DECRYPT_MODE, key);
    		dectyptedText = cipher.doFinal(text);
    } catch (Exception e) {
    		e.printStackTrace();
    }

    return new String(dectyptedText);
  }
  
  @SuppressWarnings("resource")
  public static PublicKey getPublicKey() throws FileNotFoundException, IOException, ClassNotFoundException {
	  ObjectInputStream inputStream = null;
	  inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
	  final PublicKey publicKey = (PublicKey) inputStream.readObject();
	  System.out.println ("-----BEGIN PUBLIC KEY-----");
	  System.out.println (java.util.Base64.getMimeEncoder().encodeToString(publicKey.getEncoded()));
	  System.out.println ("-----END PUBLIC KEY-----");
	  
	  return publicKey;
  }
  
  @SuppressWarnings("resource")
  public static PrivateKey getPrivateKey() throws FileNotFoundException, IOException, ClassNotFoundException {
	  ObjectInputStream inputStream = null;
	  inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
	  final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
	  System.out.println ("-----BEGIN PRIVATE KEY-----");
	  System.out.println (java.util.Base64.getMimeEncoder().encodeToString(privateKey.getEncoded()));
	  System.out.println ("-----END PRIVATE KEY-----");
	  
	  return privateKey;
  }
  
}
