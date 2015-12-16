package com.search;

import java.util.HashMap;
import java.util.Map;
public class Index {
	private String key;
	private Map<String, Integer> map = new HashMap<String, Integer>();
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Map<String, Integer> getMap() {
		return map;
	}
	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}
	
	
}