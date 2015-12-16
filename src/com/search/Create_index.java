package com.search;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.semanticweb.owlapi.model.OWLOntology;
import org.w3c.dom.Document;  
import org.w3c.dom.Element;  
import org.w3c.dom.NodeList; 

import com.search.ReadoneFile;

public class Create_index {

	/**
	 * @param args
	 */
	public static int MAXN = 100005;
	public static double sLen;
	//private static String Path = "/home/hadoop/SampleSearch/";
	private static String Path = "/home/fssqawj/workspacex/SampleSearch/";
	public static Map<String, Integer> map = new HashMap<String, Integer>();
	public static Map<String, Integer> wordcntmap = new HashMap<String, Integer>();
	public static Map<String, Integer> wmap = new HashMap<String, Integer>();
	public static Map<String, Integer> docmap = new HashMap<String, Integer>();
	public static Index[] indexs = new Index[MAXN];
	public static Doc[] docs = new Doc[MAXN];
	static ReadoneFile reof = new ReadoneFile();
	public static int hasInit = 0;
	public static void init() throws IOException{
		System.out.println("init doing...");
		
		String path = "/home/fssqawj/下载/information";
		//OWLOntology ont = m.loadOntologyFromOntologyDocument(new File("/home/fssqawj/example.owl"));

		File xfile = new File(path);
		
		String[] filelist = xfile.list();
		
		Map<String, String>cMap = new HashMap<String, String>();
		int xcnt = 0;
		for(String key : filelist){
			File readfile = new File(path + "/" + key);
			String[] flist = readfile.list();
			for(String fname : flist){
				if(xcnt % 10 == 0 || fname.contains("一线天") || fname.contains("九龙湖") || fname.contains("三亚千古情景区") || fname.contains("孔府")){
					Map<String, String> tem = reof.read(readfile.getPath() + "/" + fname);
					cMap.put(tem.get("ChName"), "has");
					
					System.out.println("xcnt = " + xcnt);
				}
				xcnt ++;
				
				//String[] tem = fname.split(".");
				//if(tem.length > 0)writer.write(tem[0] + "\n");
				//writer.write(key + " " + fname.substring(0, fname.indexOf(".")) + "\n");
			}
		}
		
		File file = new File(Path + "resultkey.txt");
        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file),"utf-8");
		BufferedWriter writer = new BufferedWriter(write);
		
		File sitefile = new File(Path + "sitekey.txt");
        OutputStreamWriter writex = new OutputStreamWriter(new FileOutputStream(sitefile),"utf-8");
		BufferedWriter writerx = new BufferedWriter(writex);
		
		Map<String,Integer> tm = new HashMap<String,Integer>();
        int top = 0;
        sLen = 0;
        int filecnt = 0;
        
        File infile = new File(Path + "outexl.xls");
        BufferedReader reader = null;
       
        reader = new BufferedReader(new FileReader(infile));
        String tempString = null;
        int scnt = 0;
        while ((tempString = reader.readLine()) != null) {
            String[] tem = tempString.split(",");
            if(!cMap.containsKey(tem[2]))continue;
            docs[filecnt] = new Doc();
            //if(tem[2].contains(query) || tem[5].contains(query))scnt ++;
            docs[filecnt].setChName(tem[2]);
            docs[filecnt].setScore(tem[3]);
            docs[filecnt].setAddr(tem[4]);
            docs[filecnt].setSummary(doSplit(tem[5]));
            docs[filecnt].setURL(tem[6]);
            docs[filecnt].setX(tem[7]);
            docs[filecnt].setY(tem[8]);
            docs[filecnt].setCountry(tem[9]);
            docs[filecnt].setCityx(tem[10]);
            docs[filecnt].setType(tem[11]);
            docs[filecnt].setTel(tem[12]);
            docs[filecnt].setWebSite(tem[13]);
            docs[filecnt].setOpenTime(tem[14]);
            docs[filecnt].setTicketInfo(tem[15]);
            docs[filecnt].setTourTime(tem[16]);
            docs[filecnt].setID(String.valueOf(filecnt));
            docs[filecnt].solveWordscnt();
            docmap.put(docs[filecnt].getID(), filecnt);
            wmap.put(docs[filecnt].getID(), docs[filecnt].getWordscnt());
            sLen += docs[filecnt].getWordscnt();
            filecnt ++;
            //if(filecnt > 1000)break;
        }
        //System.out.println("scnt = " + scnt);
        reader.close();
        writerx.close();
		
		
		
        
        /*
        for(int i = 0; i < list.getLength(); i ++){
        	docs[i] = new Doc();
        	Element element = (Element)list.item(i);  
            docs[i].setSubjectname(element.getElementsByTagName("subjectname").item(0).getFirstChild().getNodeValue());
            if(element.getElementsByTagName("dependcompany").item(0).getFirstChild()!=null)
            docs[i].setDependcompany(element.getElementsByTagName("dependcompany").item(0).getFirstChild().getNodeValue());
            docs[i].setID(element.getElementsByTagName("ID").item(0).getFirstChild().getNodeValue());
            docmap.put(docs[i].getID(), i);
            docs[i].setBegintime(element.getElementsByTagName("begintime").item(0).getFirstChild().getNodeValue());
            docs[i].setEndtime(element.getElementsByTagName("endtime").item(0).getFirstChild().getNodeValue());
            docs[i].setManager(element.getElementsByTagName("manager").item(0).getFirstChild().getNodeValue());
            docs[i].setSubjectcharacter(element.getElementsByTagName("subjectcharacter").item(0).getFirstChild().getNodeValue());
            if(element.getElementsByTagName("subjectabstract").item(0).getFirstChild()!=null)
            docs[i].setSubjectabstract(element.getElementsByTagName("subjectabstract").item(0).getFirstChild().getNodeValue());
            docs[i].setTargerinnovation(element.getElementsByTagName("targerinnovation").item(0).getFirstChild().getNodeValue());
            docs[i].setIndicators(element.getElementsByTagName("indicators").item(0).getFirstChild().getNodeValue());
            docs[i].solveWordscnt();
            wmap.put(docs[i].getID(), docs[i].getWordscnt());
            sLen += docs[i].getWordscnt();
            //System.out.println(docs[i].getWordscnt());
        }
        */
        sLen /= filecnt;
        for(int i = 0; i < filecnt; i ++)  
        {  
            String content = docs[i].getSummary() +" " + docs[i].getChName() + " " +  docs[i].getCountry() + " " + docs[i].getCityx() + " " + docs[i].getType();
            //System.out.println(content);
            String ID = docs[i].getID();
            List<String> termlist = java.util.Arrays.asList(content.split(" "));
            for(int j = 0;j < termlist.size();j ++){
            	//not find in index
            	String iTerm = termlist.get(j);
            	if(map.containsKey(iTerm) == false){
            		indexs[top] = new Index();
            		indexs[top].setKey(iTerm);
            		indexs[top].getMap().put(ID, 1);
            		map.put(iTerm, top ++);
            		wordcntmap.put(iTerm, 1);
            	}
            	else {
            		int lid = map.get(iTerm);
            		//Map<String,Integer> iMap = indexs[lid].getMap();
            		if(indexs[lid].getMap().containsKey(ID)){
            			int cnt = indexs[lid].getMap().get(ID);
            			indexs[lid].getMap().remove(ID);indexs[lid].getMap().put(ID, cnt + 1);
            		}
            		else {
            			indexs[lid].getMap().put(ID, 1);
            		}
            		wordcntmap.put(iTerm, wordcntmap.get(iTerm) + 1);
            	}
            }
        }
        
        
        for(int i = 0;i < top;i ++){
        	writer.write("Key : " + indexs[i].getKey() + "\n");
        	for(String key:indexs[i].getMap().keySet()){
        		writer.write(key + " " + indexs[i].getMap().get(key) + "\n");
        	}
        }
        writer.close();
	}
	public static List<Unit> create(String query) throws Exception {
		// TODO Auto-generated method stub
		/*
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
        
        DocumentBuilder db = dbf.newDocumentBuilder();  
         
        Document document = db.parse(new File(Path + "req_result.xml"));  
          
        NodeList list = document.getElementsByTagName("Pro");
        */
        if(hasInit == 0){
        	init();
        	hasInit = 1;
        }
		File file = new File(Path + "TF-IDF_result_x.txt");
		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file),"utf-8");
		BufferedWriter writer = new BufferedWriter(write);
        
        System.out.println(query);
        List<Term> lis = ToAnalysis.parse(query);
        
        List<List<Unit>> res1 = new ArrayList<List<Unit>>();
       // List<List<Unit>> res2 = new ArrayList<List<Unit>>();
        for(int i = 0;i < lis.size();i ++){
        	String tem = lis.get(i).toString();
        	System.out.println(tem);
        	String[] ary = tem.split("/");
        	String term = "";
        	double e = ((ary[1].contains("w")||ary[1].contains("nr")) ? 100 : 1);
			if(!Stopwords.isstop(ary[0]) && ary.length > 0){
				term = ary[0];
				System.out.println(e);
				List<Unit> t1 = TFIDF(term);
				
				res1.add(Normal(t1,e));
				//List<Unit> t2 = BM25(term);
				//res2.add(Normal(t2));
			}
        }
        Map<String, Double> res = new HashMap<String, Double>();
        for(int i = 0;i < res1.size();i ++){
        	for(int j = 0;j < res1.get(i).size();j ++){
        		String iDoc = res1.get(i).get(j).getDocId();
        		if(res.containsKey(iDoc)){
        			double tem = res.get(iDoc);
        			res.remove(iDoc);
        			res.put(iDoc, tem + res1.get(i).get(j).getSrc());
        		}
        		else {
        			res.put(iDoc, res1.get(i).get(j).getSrc());
        		}
        	}
        }
        List<Unit> iRes = new ArrayList<Unit>();
        for(String key:res.keySet()){
        	Unit t = new Unit();
        	int id = docmap.get(key);
        	t.setDocId(key);
        	t.setChName(docs[id].getChName());
        	t.setAddr(docs[id].getAddr());
        	t.setURL(docs[id].getURL());
        	t.setType(docs[id].getType());
        	t.setSrc(res.get(key));
        	iRes.add(t);
        }
        Collections.sort(iRes);
        System.out.println("size = " + iRes.size());
        for(int i = 0;i < iRes.size();i ++){
        	writer.write(iRes.get(i).getDocId() + " " + iRes.get(i).getSrc() + "\n");
        }
        writer.close();
        /*
        double maxx = 0,minn = 1e11;
        for(int i = 0;i < res.size();i ++){
        	//writer.write(res.get(i).getDocId() + " " + res.get(i).getNum() + "\n");
        	double tem = res.get(i).getNum();
        	maxx = Math.max(maxx, tem);minn = Math.min(minn, tem);
        }
        for(int i = 0;i < res.size();i ++){
        	double tem = res.get(i).getNum();
        	res.get(i).setNum(10 * (tem - minn) / (maxx - minn));
        	writer.write(res.get(i).getDocId() + " " + res.get(i).getNum() + "\n");
        }
        writer.close();
        
        writer.close();
        file = new File("TF-IDF_result.txt");
        write = new OutputStreamWriter(new FileOutputStream(file),"utf-8");
		writer = new BufferedWriter(write);
		
		*/
        System.out.println("done");
        
        return iRes;
	}
	public static List<Unit> Normal(List<Unit> res,double e){
		double maxx = 0,minn = 1e11;
        for(int i = 0;i < res.size();i ++){
        	//writer.write(res.get(i).getDocId() + " " + res.get(i).getNum() + "\n");
        	double tem = res.get(i).getSrc();
        	maxx = Math.max(maxx, tem);minn = Math.min(minn, tem);
        }
        for(int i = 0;i < res.size();i ++){
        	double tem = res.get(i).getSrc();
        	res.get(i).setSrc(e * 10 * (tem - minn) / (maxx - minn));
        	//writer.write(res.get(i).getDocId() + " " + res.get(i).getNum() + "\n");
        }
        return res;
	}
	public static List<Unit> DFR(String query){
		int iId = 0;
		if(map.containsKey(query))iId = map.get(query);
		List<Unit> res = new ArrayList<Unit>();
        Map<String, Integer> iMap = indexs[iId].getMap();
        for(String key:iMap.keySet()){
        	Unit unit = new Unit();
        	int id = docmap.get(key);
        	unit.setDocId(key);
        	unit.setChName(docs[id].getChName());
        	unit.setAddr(docs[id].getAddr());
        	unit.setURL(docs[id].getURL());
        	unit.setType(docs[id].getType());
        	double r = (wordcntmap.containsKey(key) ? 1.0 * wordcntmap.get(key) / docs.length : 0);
    		double l = iMap.get(key) / wmap.get(key) * (sLen / docs.length);
    		if(r == 0)unit.setSrc(0);
    		else {
	        	double pro1 = -Math.log(1.0 / (1 + r)) - l * Math.log(r / (1 + r));
	        	double pro2 = 1.0 / (l + 1);
	    		unit.setSrc(pro1 * pro2);
    		}
        	res.add(unit);
        }
        Collections.sort(res);
        return res;
	}
	public static List<Unit> TFIDF(String query){
		int iId = 0;
		if(map.containsKey(query))iId = map.get(query);
		List<Unit> res = new ArrayList<Unit>();
        Map<String, Integer> iMap = indexs[iId].getMap();
        for(String key:iMap.keySet()){
        	Unit unit = new Unit();
        	int id = docmap.get(key);
        	unit.setDocId(key);
        	unit.setChName(docs[id].getChName());
        	unit.setAddr(docs[id].getAddr());
        	unit.setURL(docs[id].getURL());
        	unit.setType(docs[id].getType());
        	unit.setSrc(iMap.get(key) * 1000.0 / wmap.get(key));
        	res.add(unit);
        }
        Collections.sort(res);
        return res;
	}
	public static List<Unit> BM25(String query){
		int iId = map.get(query);
		List<Unit> res = new ArrayList<Unit>();
        Map<String, Integer> iMap = indexs[iId].getMap();
        for(String key:iMap.keySet()){
        	Unit unit = new Unit();
        	int id = docmap.get(key);
        	//unit.setDocId(key);
        	unit.setChName(docs[id].getChName());
        	unit.setAddr(docs[id].getAddr());
        	unit.setURL(docs[id].getURL());
        	unit.setType(docs[id].getType());
        	unit.setDocId(key);
        	unit.setSrc(2 * iMap.get(key) / (0.25 + 0.75 * wmap.get(key) / sLen + iMap.get(key)));
        	res.add(unit);
        }
        Collections.sort(res);
        return res;
	}
	public static String doSplit(String content){
		List<Term> lis = ToAnalysis.parse(content);
        String res = "";
		for(int j = 0;j < lis.size();j ++){
			String tem = lis.get(j).toString();
			if(tem.charAt(tem.length()-1) != 'n')continue;
			String[] ary = new String[100];
			ary = tem.split("/");
			if(ary.length > 0)tem = ary[0];
			res += tem + " ";
		}
		return res;
	}
}