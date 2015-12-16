package com.fileop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.search.ReadoneFile;

public class ChangeFile {
	static ReadoneFile reof = new ReadoneFile();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/*
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
					xcnt ++;
					System.out.println(xcnt);
				}
				
				
				//String[] tem = fname.split(".");
				//if(tem.length > 0)writer.write(tem[0] + "\n");
				//writer.write(key + " " + fname.substring(0, fname.indexOf(".")) + "\n");
			}
		}
		*/
		File infile = new File("/home/fssqawj/workspacex/SampleSearch/queryset.txt");
		BufferedReader reader = null;
		reader = new BufferedReader(new FileReader(infile));
		
		File outfile = new File("/home/fssqawj/workspacex/SampleSearch/queryset_small.txt");
		BufferedWriter writer = null;
		writer = new BufferedWriter(new FileWriter(outfile));
		
		String line = "";
		while((line = reader.readLine()) != null){
			if(line.contains("null"))continue;
			writer.write(line + "\n");
		}
		reader.close();
		writer.close();
	}

}
