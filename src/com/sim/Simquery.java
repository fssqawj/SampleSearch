package com.sim;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.ansj.splitWord.analysis.NlpAnalysis;

public class Simquery {
	//private static String Path = "/home/hadoop/SampleSearch/";
	private static String Path = "/home/fssqawj/workspacex/SampleSearch/";
	public List<String> simquery(String query){
	//public void main(){
		File filename = new File(Path + "queryset_small.txt");
        InputStreamReader reader;
        List<String> res = new ArrayList<String>();
        List<Query> querys = new ArrayList<Query>();
        int cnt = 0;
		try {
			reader = new InputStreamReader(new FileInputStream(filename),"utf-8");
			BufferedReader br = new BufferedReader(reader);
			String line = br.readLine();
			while(line != null){
				//System.out.println(line);
				if(cnt ++ > 50000)break;
				String[] tem = line.split("		");
				//System.out.println(tem);
				Query q = new Query();
				q.setQuery(tem[0]);
				if(tem.length > 1)q.setResult(tem[1]);
				q.setQuerylist(ToAnalysis.parse(q.getQuery()));
				sim(q,query);
				querys.add(q);
				line = br.readLine();
			}
			reader.close();
			System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
			Collections.sort(querys);
			for(int i = 0;i < 10;i ++){
				res.add(querys.get(i).getQuery() + "#" + querys.get(i).getResult() + "#" + querys.get(i).getSrc());
				//System.out.println(querys.get(i).getSrc());
			}
			//System.out.println("src0 = " + querys.get(0).getSrc());
			//System.out.println(ToAnalysis.parse(query));
			//System.out.println(ToAnalysis.parse(querys.get(0).getQuery()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	public static void sim(Query q,String qs){
		List<Term> term = ToAnalysis.parse(q.getQuery());
		List<Term> tx = ToAnalysis.parse(qs);
		int cnt = 0;
		for(Term i : term){
			for(Term j : tx){
				//System.out.println(i.toString() + " " + j.toString());
				if(i.toString().compareTo(j.toString()) == 0){
					cnt ++;
					break;
				}
			}
		}
		//System.out.println(cnt);
		q.setSrc(1.0 * cnt / Math.sqrt(tx.size() * term.size()));
		//q.setSrc(1);
	}
}
