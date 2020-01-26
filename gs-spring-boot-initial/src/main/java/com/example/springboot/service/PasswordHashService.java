package com.example.springboot.service;

import java.security.MessageDigest;

public final class PasswordHashService {

	public static String hashPassword(String mdp) throws Exception {
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(mdp.getBytes());
			
		byte byteData[] = md.digest();
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
}
