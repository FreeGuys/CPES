package com.atguigu.crowdfunding.util;

public class StringUtil {

	public static boolean isEmpty( String s ) {
		if ( s == null || "".equals(s) ) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isNotEmpty( String s ) {
		return !isEmpty(s);
	}
}
