package com.search;

public class Unit implements Comparable<Object>{
	private String DocId;
	private String ChName;
	private String addr;
	private String type;

	private String URL;
	private double src;
	
	public String getDocId() {
		return DocId;
	}

	public void setDocId(String docId) {
		DocId = docId;
	}

	public String getChName() {
		return ChName;
	}

	public void setChName(String chName) {
		ChName = chName;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public double getSrc() {
		return src;
	}

	public void setSrc(double src) {
		this.src = src;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Unit p = (Unit)o;
		if(this.getSrc() > p.getSrc())return -1;
		return 1;
	}
	
}