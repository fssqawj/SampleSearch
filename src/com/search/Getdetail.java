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
import org.w3c.dom.Document;  
import org.w3c.dom.Element;  
import org.w3c.dom.NodeList; 
public class Getdetail {
	public static int MAXN = 100005;
	private static Map<String,Doc> detail = new HashMap<String,Doc>();
	private static String Path = "/home/fssqawj/workspacex/SampleSearch/";
	public static Doc[] docs = new Doc[MAXN];
	public static Map<String,Doc> create() throws Exception {
			// TODO Auto-generated method stub
			
		int filecnt = 0;
        
        File infile = new File(Path + "outexl.xls");
        BufferedReader reader = null;
       
        reader = new BufferedReader(new FileReader(infile));
        String tempString = null;
        
        while ((tempString = reader.readLine()) != null) {
            String[] tem = tempString.split(",");
            docs[filecnt] = new Doc();
            docs[filecnt].setChName(tem[2]);
            docs[filecnt].setScore(tem[3]);
            docs[filecnt].setAddr(tem[4]);
            docs[filecnt].setSummary(tem[5]);
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
            detail.put(String.valueOf(filecnt), docs[filecnt]);
            filecnt ++;
        }
        reader.close();
        return detail;
	}
	
}
