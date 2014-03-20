package common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Write {
	public static void writeToFile(String oFileDir, String oFileName, ArrayList<String> list, boolean isAddWriting){
		File writeFile=new File(oFileDir);  
        if(!writeFile.exists()){  
            writeFile.mkdirs();  
        } 
        
		try{
			File file = new File(oFileName);
			BufferedWriter bw = null;
			if(isAddWriting==true){
				bw = new BufferedWriter(new FileWriter(file, true));
			}else{
				bw = new BufferedWriter(new FileWriter(file));
			}
			for(String s : list){
				bw.write(s);
				bw.newLine();
			}
			bw.flush();
			bw.close();
		}catch(IOException ioe){
			System.out.println("Fail to create the file " + oFileName + "!");
		}
	}
	
	public static void writeToFileNotRewrite(String oFileDir, String oFileName, ArrayList<String> list){
		File writeFile=new File(oFileDir);  
        if(!writeFile.exists()){  
            writeFile.mkdirs();  
        } 
        
		try{
			File file = new File(oFileName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
			for(String s : list){
				bw.write(s);
				bw.newLine();
			}
			bw.flush();
			bw.close();
		}catch(IOException ioe){
			System.out.println("Fail to create the file " + oFileName + "!");
		}
	}
	
	public static void writeToFile(String oFileDir, String oFileName, 
			ArrayList<String[]> arr_list, String label, boolean isAddWriting){
		File writeFile=new File(oFileDir);  
        if(!writeFile.exists()){  
            writeFile.mkdirs();  
        } 
        
		try{
			File file = new File(oFileName);
			BufferedWriter bw = null;
			if(isAddWriting==true){
				bw = new BufferedWriter(new FileWriter(file, true));
			}else{
				bw = new BufferedWriter(new FileWriter(file));
			}

			int size = arr_list.size();
			for(int i=0; i<size; i++){
				String[] arr = arr_list.get(i);
				int len = arr.length;
				String str = "";
				for(int j=0; j<len; j++){
					if(j==0)
						str += arr[j];
					else
						str += label+arr[j];
				}
				bw.write(str);
				bw.newLine();
			}
			bw.flush();
			bw.close();
		}catch(IOException ioe){
			System.out.println("Fail to create the file " + oFileName + "!");
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void writeToFile(String oFileDir, String oFileName, HashMap map, String label, boolean isRewrite){
		String keyStr = null;
		String valStr = null;
		File writeFile=new File(oFileDir);  
        if(!writeFile.exists()){  
            writeFile.mkdirs();  
        }
        
		try{
			File file = new File(oFileName);
			BufferedWriter bw = null;
			if(isRewrite==true){
				bw = new BufferedWriter(new FileWriter(file));
			}else{
				bw = new BufferedWriter(new FileWriter(file, true));
			}
			for(Object key : map.keySet()){
				Object val = map.get(key);
				keyStr = key.toString();
				if(val==null)
					valStr = "null";
				else
					valStr = val.toString();
				
				bw.write(keyStr+label+valStr);
				bw.newLine();
			}
			bw.flush();
			bw.close();
		}catch(IOException ioe){
			System.out.println("Fail to create the file!");
		}
	}
	
	public static void writeToFile(String oFileDir, String oFileName, String str, boolean isRewrite){
		String keyStr = null;
		String valStr = null;
		File writeFile=new File(oFileDir);  
        if(!writeFile.exists()){  
            writeFile.mkdirs();  
        }
        
		try{
			File file = new File(oFileName);
			BufferedWriter bw = null;
			if(isRewrite==true){
				bw = new BufferedWriter(new FileWriter(file));
			}else{
				bw = new BufferedWriter(new FileWriter(file, true));
			}
			
			bw.write(str);
			bw.newLine();
			
			bw.flush();
			bw.close();
		}catch(IOException ioe){
			System.out.println("Fail to create the file!");
		}
	}
	
	public static void writeToFile(String oFileDir, String oFileName, List<Map.Entry<String, Integer>> list, String label){
		String keyStr = null;
		String valStr = null;
		File writeFile=new File(oFileDir);  
        if(!writeFile.exists()){  
            writeFile.mkdirs();  
        } 
        
        try{
			File file = new File(oFileName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for(int i=0; i<list.size(); i++){
				keyStr = list.get(i).getKey();
				valStr = Integer.toString(list.get(i).getValue());
				bw.write(keyStr+label+valStr);
				bw.newLine();
			}
			bw.flush();
			bw.close();
		}catch(IOException ioe){
			System.out.println("Fail to create the file!");
		}
	}
	
	public static void writeToFile(String oFileDir, String oFileName, 
			ArrayList<ArrayList<String>> listList, String separate){
		File writeFile=new File(oFileDir);  
        if(!writeFile.exists()){  
            writeFile.mkdirs();  
        } 
        
        try{
			File file = new File(oFileName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for(int i=0; i<listList.size(); i++){
				ArrayList<String> list = listList.get(i);
				int listSize = list.size();
				for(int j=0; j<listSize; j++){
					String ele = list.get(j);
					if(j==0)
						bw.write(ele);
					else
						bw.write(separate+ele);
				}
				bw.newLine();
			}
			bw.flush();
			bw.close();
		}catch(IOException ioe){
			System.out.println("Fail to create the file!");
		}
	}
	
	public static void writeToFile1(String oFileDir, String oFileName, HashMap<String, ArrayList<String>> map, boolean isRewrite){
		String keyStr = null;
		ArrayList<String> valsList = new ArrayList<String>();
		File writeFile=new File(oFileDir);  
        if(!writeFile.exists()){  
            writeFile.mkdirs();  
        } 
        
		try{
			File file = new File(oFileName);
			BufferedWriter bw = null;
			if(isRewrite==true){
				bw = new BufferedWriter(new FileWriter(file));
			}else{
				bw = new BufferedWriter(new FileWriter(file, true));
			}
			
			for(Object key : map.keySet()){
				Object val = map.get(key);
				keyStr = key.toString();
				valsList = map.get(keyStr);
				bw.write(keyStr+"=====>");
				if(valsList!=null){
					for(String s : valsList){
						bw.write(s+" ");
					}
				}
				
				
				bw.newLine();
			}
			bw.flush();
			bw.close();
		}catch(IOException ioe){
			System.out.println("Fail to create the file!");
		}
	}
	
	public static void writeToFile2(String oFileDir, String oFileName, HashMap<String, ArrayList<Double>> map){
		String keyStr = null;
		ArrayList<Double> valsList = new ArrayList<Double>();
		File writeFile=new File(oFileDir);  
        if(!writeFile.exists()){  
            writeFile.mkdirs();
        } 
        
		try{
			File file = new File(oFileName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			for(Object key : map.keySet()){
				Object val = map.get(key);
				keyStr = key.toString();
				valsList = map.get(keyStr);
				bw.write(keyStr+"=====>");
				if(valsList!=null){
					for(double s : valsList){
						bw.write(s+" ");
					}
				}
				
				
				bw.newLine();
			}
			bw.flush();
			bw.close();
		}catch(IOException ioe){
			System.out.println("Fail to create the file!");
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void writeToFile3(String oFileDir, String oFileName, 
			HashMap<Pair<String, String>, Boolean> map, String split){
		File writeFile=new File(oFileDir);
        if(!writeFile.exists()){
            writeFile.mkdirs(); 
        }
        
		try{
			File file = new File(oFileName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			Iterator iter = map.entrySet().iterator();
			while(iter.hasNext()){
				Map.Entry entry = (Map.Entry) iter.next();
				Pair<String, String> pair = (Pair<String, String>) entry.getKey();
				boolean flag = (Boolean) entry.getValue();
				System.out.println("<\""+pair.getFirst()+"\", \""+pair.getSecond()+"\">"+split+flag);
				bw.newLine();
			}
			bw.flush();
			bw.close();
		}catch(IOException ioe){
			System.out.println("Fail to create the file!");
		}
	}
	
	public static void writeToFile4(String oFileDir, String oFileName, 
			HashMap<Pair<String, String>, Double> map, String split){
		File writeFile=new File(oFileDir);
        if(!writeFile.exists()){
            writeFile.mkdirs(); 
        }
        
		try{
			File file = new File(oFileName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			Iterator iter = map.entrySet().iterator();
			while(iter.hasNext()){
				Map.Entry entry = (Map.Entry) iter.next();
				Pair<String, String> pair = (Pair<String, String>) entry.getKey();
				bw.write(pair.getFirst()+"  :- "+pair.getSecond()+split+entry.getValue());
				bw.newLine();
			}
			bw.flush();
			bw.close();
		}catch(IOException ioe){
			System.out.println("Fail to create the file!");
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args){
		Pair<String, String> pairTmp1 = new Pair<String, String>("film1", "director1");
		Pair<String, String> pairTmp2 = new Pair<String, String>("film2", "director2");
		HashMap<Pair<String, String>, Boolean> map = new HashMap<Pair<String, String>, Boolean>();
		map.put(pairTmp1, true);
		map.put(pairTmp2, false);
		Iterator iter = map.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry entry = (Map.Entry) iter.next();
			Pair<String, String> pair = (Pair<String, String>) entry.getKey();
			boolean flag = (Boolean) entry.getValue();
			System.out.println(pair.getFirst()+"---"+pair.getSecond()+"---"+flag);
		}
	}
}
