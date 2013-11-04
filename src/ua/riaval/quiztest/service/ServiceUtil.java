package ua.riaval.quiztest.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ServiceUtil {

	private static final String SOLD = "$iAm#?";

	public static String sha256(String text) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(text.getBytes());

		byte byteData[] = md.digest();

		// convert the byte to hex format method 1
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			hexString.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
					.substring(1));
		}

		return hexString.toString();
	}

	public static String getSoldHash(String text)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return sha256(text + SOLD);
	}

}
