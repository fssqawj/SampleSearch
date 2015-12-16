package com.fenci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.ansj.splitWord.analysis.NlpAnalysis;

import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  
  






import org.w3c.dom.Document;  
import org.w3c.dom.Element;  
import org.w3c.dom.NodeList; 

public class Fenci {
	private static String Path = "/home/hadoop/SampleSearch/";
	public static void doFenCi() throws Exception {
		// TODO Auto-generated method stub
		/*String str = "Œ¯³ÉµçÂ·ÖÐº¬ÓÐ¹èÆ¬" ;
		List<Term> list = ToAnalysis.parse(str);
		for(int i = 0;i < list.size();i ++){
			System.out.println(list.get(i).toString());
		}*/
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
        
        DocumentBuilder db = dbf.newDocumentBuilder();  
           
        Document document = db.parse(new File(Path + "req.xml"));  
          
        NodeList list = document.getElementsByTagName("Pro");
        File file = new File(Path + "req_result.xml");
        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file),"utf-8");
		BufferedWriter writer = new BufferedWriter(write);
		
		
		
		//writer.close();
		writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + "\n" + "<Pros xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + "\n");
        for(int i = 0; i < list.getLength(); i++)  
        {  
            Element element = (Element)list.item(i);  
            writer.write("	<Pro>\n");
            writer.write("		<subjectname>");
            String content = element.getElementsByTagName("subjectname").item(0).getFirstChild().getNodeValue();  
            writer.write(content);
    		writer.write("</subjectname>\n");
    		writer.write("		<dependcompany>");
            content = element.getElementsByTagName("dependcompany").item(0).getFirstChild().getNodeValue();  
            writer.write(content);
    		writer.write("</dependcompany>\n");
    		
    		
    		writer.write("		<ID>");
            content = element.getElementsByTagName("ID").item(0).getFirstChild().getNodeValue();  
    		writer.write(content);
    		writer.write("</ID>\n");
    		writer.write("		<begintime>");
            content = element.getElementsByTagName("begintime").item(0).getFirstChild().getNodeValue();  
    		writer.write(content);
    		writer.write("</begintime>\n");
    		writer.write("		<endtime>");
            content = element.getElementsByTagName("endtime").item(0).getFirstChild().getNodeValue();  
    		writer.write(content);
    		writer.write("</endtime>\n");
    		writer.write("		<manager>");
            content = element.getElementsByTagName("manager").item(0).getFirstChild().getNodeValue();  
    		writer.write(content);
    		writer.write("</manager>\n");
    		writer.write("		<subjectcharacter>");
            content = element.getElementsByTagName("subjectcharacter").item(0).getFirstChild().getNodeValue();  
    		writer.write(content);
    		writer.write("</subjectcharacter>\n");
    		
    		writer.write("		<subjectabstract>");
            content = element.getElementsByTagName("subjectabstract").item(0).getFirstChild().getNodeValue();  
            
            List<Term> lis = ToAnalysis.parse(content);
            
    		for(int j = 0;j < lis.size();j ++){
    			String tem = lis.get(j).toString();
    			if(tem.charAt(tem.length()-1) != 'n')continue;
    			String[] ary = new String[100];
    			ary = tem.split("/");
    			if(ary.length > 0)tem = ary[0];
    			writer.write(tem + " ");
    		}
    		writer.write("</subjectabstract>\n");
    		
    		writer.write("		<targerinnovation>");
            content = element.getElementsByTagName("targerinnovation").item(0).getFirstChild().getNodeValue();  
            
            lis = ToAnalysis.parse(content);
            
    		for(int j = 0;j < lis.size();j ++){
    			String tem = lis.get(j).toString();
    			if(tem.charAt(tem.length()-1) != 'n')continue;
    			String[] ary = new String[100];
    			ary = tem.split("/");
    			if(ary.length > 0)tem = ary[0];
    			writer.write(tem + " ");
    		}
    		writer.write("</targerinnovation>\n");
    		
    		writer.write("		<indicators>");
            content = element.getElementsByTagName("indicators").item(0).getFirstChild().getNodeValue();  
            
            lis = ToAnalysis.parse(content);
            
    		for(int j = 0;j < lis.size();j ++){
    			String tem = lis.get(j).toString();
    			if(tem.charAt(tem.length()-1) != 'n')continue;
    			String[] ary = new String[100];
    			ary = tem.split("/");
    			if(ary.length > 0)tem = ary[0];
    			writer.write(tem + " ");
    		}
    		writer.write("</indicators>\n");
    		writer.write("	</Pro>\n");
        }
        writer.write("</Pros>");
        writer.close();
        System.out.println("done");
	}

}