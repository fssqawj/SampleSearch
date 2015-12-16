package com.change;
import java.util.List;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
public class Change {
	public static String ChangeSt(String s,String strx){
		List<Term> lis = ToAnalysis.parse(strx);
		//System.out.println(strx + " " + lis.size());
		for(Term t:lis){
			String[] ary = t.toString().split("/");
			if(ary.length > 1 && !ary[1].contains("n") && !ary[1].contains("w"))continue;
			String str = ary[0];
			//System.out.println("s = " + s + " str = " + str);
			int id = s.indexOf(str);
			if(id == -1)continue;
			s = s.substring(0, id) + "<em>" + s.substring(id, id + str.length()) + "</em>" + s.substring(id + str.length(),s.length());
		}
		return s;
	}
}
