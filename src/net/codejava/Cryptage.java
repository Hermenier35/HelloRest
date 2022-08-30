package net.codejava;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Cryptage {
	
	public static String cryptage(String text, String key) {
		try {
			 Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
	         Cipher cipher = Cipher.getInstance("AES");
	         // encrypt the text
	         cipher.init(Cipher.ENCRYPT_MODE, aesKey);
	         byte[] encrypted = cipher.doFinal(text.getBytes());
	         StringBuilder sb = new StringBuilder();
	            for (byte b: encrypted) {
	                sb.append((char)b);
	            }
	         return sb.toString();
			}catch (Exception e) {
				// TODO: handle exception
			}
		return "error";
	}
	
	public static String deCryptage(String encrypted, String key) {
		try {
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			 byte[] bb = new byte[encrypted.length()];
	            for (int i=0; i<encrypted.length(); i++) {
	                bb[i] = (byte) encrypted.charAt(i);
	            }
			String decrypted = new String(cipher.doFinal(bb));
			return decrypted;
			}catch (Exception e) {
				// TODO: handle exception
			}
		return "error";
	}
	
	public static String loginKeys(String access) {
		if(access.equals("admins"))
			return Cryptage.cryptage(CodeAcces.ADMINACCESS, CodeAcces.KEY);
		if(access.equals("parent"))
			return Cryptage.cryptage(CodeAcces.PARENTACCESS, CodeAcces.KEY);
		if(access.equals("prof"))
			return Cryptage.cryptage(CodeAcces.PROFACCESS, CodeAcces.KEY);
		
		return "error";
	}
	  
}
