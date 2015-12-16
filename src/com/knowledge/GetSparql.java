package com.knowledge;
import java.util.List;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.ansj.splitWord.analysis.NlpAnalysis;
public class GetSparql {
	
	public static String get(String query){
		//String query = "位于苏州的遗庙古迹";
		List<Term> lis = ToAnalysis.parse(query);
		
		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
	        	"PREFIX owl: <http://www.w3.org/2002/07/owl#>" + 
	        		"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" + 
	        		"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" + 
	        		"PREFIX my: <http://www.semanticweb.org/fssqawj/ontologies/2015/2/untitled-ontology-2#>"; 
	   //     		"SELECT ?subject WHERE { ?subject my:locateProvince my:浙江 } limit 10";  
		
		if(query.contains("位于") && query.contains("景点有")){
			for(Term key : lis){
				String[] tem = key.toString().split("/");
				if(tem.length > 1 && tem[1].contains("ns")){
					System.out.println(tem[0]);
					queryString += "SELECT ?subject WHERE { ?subject my:locateProvince my:" + tem[0] +" } limit 10";
					return queryString;
				}
				//System.out.println(key.toString());
			}
		}
		else if(query.contains("在相同地方") && query.contains("景点")){
			for(Term key : lis){
				String[] tem = key.toString().split("/");
				if(tem.length > 1 && tem[1].contains("mw")){
					System.out.println(tem[0]);
					queryString += "SELECT ?subject WHERE { ?subject my:locateCity ?o. my:" + tem[0] + " my:locateCity ?o.} limit 10";
					return queryString;
				}
				//System.out.println(key.toString());
			}
		}
		else if(query.contains("在什么地方")){
			for(Term key : lis){
				String[] tem = key.toString().split("/");
				if(tem.length > 1 && tem[1].contains("mw")){
					System.out.println(tem[0]);
					queryString += "SELECT ?subject WHERE { my:" + tem[0] + " my:locateCity ?subject.} limit 10";
					return queryString;
				}
				//System.out.println(key.toString());
			}
		}
		return null;
		//System.out.println(query.length());
	}
}
