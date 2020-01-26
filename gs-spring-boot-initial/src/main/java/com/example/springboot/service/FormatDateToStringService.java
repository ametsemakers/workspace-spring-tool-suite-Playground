package com.example.springboot.service;

import java.text.SimpleDateFormat;

public class FormatDateToStringService {

	private static String pattern = "yyy-MM-dd";
	private static SimpleDateFormat simpleDateFormat;
	
	public static SimpleDateFormat formatDate() {
		
		return setSimpleDateFormat(new SimpleDateFormat(pattern));
	}

	public static SimpleDateFormat getSimpleDateFormat() {
		return simpleDateFormat;
	}

	public static SimpleDateFormat setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
		FormatDateToStringService.simpleDateFormat = simpleDateFormat;
		return simpleDateFormat;
	}
}
