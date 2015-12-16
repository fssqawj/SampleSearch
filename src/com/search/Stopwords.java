package com.search;

public class Stopwords {
	public static Boolean isstop(String str){
		if(str.contains("项目"))return true;
		return false;
	}
}
