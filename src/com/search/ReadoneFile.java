package com.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReadoneFile {
	public Map<String,String> read(String filename){
		File file = new File(filename);
        BufferedReader reader = null;
        Map<String,String> res = new HashMap<String, String>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            
            while ((tempString = reader.readLine()) != null) {
                String[] tem = tempString.split("	");
                //if(tem.length == 0)continue;
                //if(tem.length >= 2 && (tem[1].contains("http") || tem[1].contains("地址")))tem[1] = tem[2];
                if(tem.length > 1)res.put(tem[0].trim(), tem[1].trim());
                else res.put(tem[0], "NULL");
            }
            reader.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
            
        }
        return res;
	}
}
