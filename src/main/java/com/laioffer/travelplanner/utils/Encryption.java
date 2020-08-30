package com.laioffer.travelplanner.utils;

import java.security.Key;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

//import java.util.Base64;

public class Encryption {

	public static final String SALT = "my-salt-text";
	private static final String ALGO = "AES";
	private static final byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't',
			'K', 'e', 'y' };

	public static String saltPassword(String password) {

		String saltedPassword = SALT + password;
		String hashedPassword = generateHash(saltedPassword);
		return hashedPassword;

	}

	private static String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return hash.toString();
	}

//	public static String encrypt(String Data) throws Exception {
//		String encryptedValue = Base64.getEncoder().encodeToString(
//	            Data.getBytes("utf-8"));
//		return encryptedValue;
//	}
//
//	public static String decrypt(String encryptedData) throws Exception {
//        byte[] base64decodedBytes = Base64.getDecoder().decode(encryptedData);
//		
//        System.out.println("Original String: " + new String(base64decodedBytes, "utf-8"));
//		return new String(base64decodedBytes, "utf-8");
//	}

	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGO);
		return key;
	}

	public static void main(String[] args) throws Exception {
		String password = "mypassword";
	}
}
