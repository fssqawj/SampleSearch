package com.search;

public class Doc {
	private String ID = new String();
	private String ChName = new String();
	private String score = new String();
	private String addr = new String();
	private String summary = new String();
	private String URL = new String();
	private String X = new String();
	private String Y = new String();
	private String Country = new String();
	private String Cityx = new String();
	private String Type = new String();
	private String Tel = new String();
	private String WebSite = new String();
	private String OpenTime = new String();
	private String TicketInfo = new String();
	private String TourTime = new String();
	private int wordscnt;
	public void solveWordscnt() {
		this.wordscnt = summary.split(" ").length;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getChName() {
		return ChName;
	}
	public void setChName(String chName) {
		ChName = chName;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getX() {
		return X;
	}
	public void setX(String x) {
		X = x;
	}
	public String getY() {
		return Y;
	}
	public void setY(String y) {
		Y = y;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getCityx() {
		return Cityx;
	}
	public void setCityx(String cityx) {
		Cityx = cityx;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getTel() {
		return Tel;
	}
	public void setTel(String tel) {
		Tel = tel;
	}
	public String getWebSite() {
		return WebSite;
	}
	public void setWebSite(String webSite) {
		WebSite = webSite;
	}
	public String getOpenTime() {
		return OpenTime;
	}
	public void setOpenTime(String openTime) {
		OpenTime = openTime;
	}
	public String getTicketInfo() {
		return TicketInfo;
	}
	public void setTicketInfo(String tiketInfo) {
		TicketInfo = tiketInfo;
	}
	public String getTourTime() {
		return TourTime;
	}
	public void setTourTime(String tourTime) {
		TourTime = tourTime;
	}
	public int getWordscnt() {
		return wordscnt;
	}
	public void setWordscnt(int wordscnt) {
		this.wordscnt = wordscnt;
	}
	
}