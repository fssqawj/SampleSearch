package com.sim;

import java.util.ArrayList;
import java.util.List;
import org.ansj.domain.Term;
public class Query implements Comparable<Object>{
	String query = new String();
	String result = new String();
	List<Term> querylist = new ArrayList<Term>();
	double src;
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<Term> getQuerylist() {
		return querylist;
	}
	public void setQuerylist(List<Term> querylist) {
		this.querylist = querylist;
	}
	public double getSrc() {
		return src;
	}
	public void setSrc(double src) {
		this.src = src;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Query q = (Query)o;
		if(this.getSrc() > q.getSrc())return -1;
		else if(this.getSrc() == q.getSrc())return 0;
		return 1;
	}
	
}
