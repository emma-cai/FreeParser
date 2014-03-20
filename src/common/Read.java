package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Read {
	public static ArrayList<String> readDir(String iFileDir) throws InterruptedException{
		ArrayList<String> dirsList = new ArrayList<String>();
		String name = null;
		File file = new File(iFileDir);
		if(!file.isDirectory()){
			System.out.println("This is a file!\t"+iFileDir);
		}else if(file.isDirectory()){
			String[] fileList = file.list();
			
			for(int i=0; i<fileList.length; i++){
				name = fileList[i];
//				path = iFileDir+"//"+name;
//				if(!dirsList.contains(path))
//					dirsList.add(path);
				
				if(!dirsList.contains(name))
					dirsList.add(name);
			}	
		}
		return dirsList;
	}
	
	public static ArrayList<String> readFile(String iFileName, boolean isRedudant){
		ArrayList<String> linesList = new ArrayList<String>();
		File iFile = new File(iFileName);
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(iFile));
			String line = br.readLine();
			while(line!=null){
				if(isRedudant==true){
					if(!line.equals(""))
						linesList.add(line);
				}else{
					if(!line.equals("") && !linesList.contains(line))
						linesList.add(line);
				}
				
				line = br.readLine();
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(br != null){
				try{
					br.close();
				}catch(IOException el){
					
				}
			}
		}
		return linesList;
	}
	
	public static void readFile(String iFileName, ArrayList<ArrayList<String>> data, String label){
		File iFile = new File(iFileName);
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(iFile));
			String line = br.readLine();
			while(line!=null){
				ArrayList<String> dataOneLine = common.Operator.SenToList(line, "\t");
				data.add(dataOneLine);
				line = br.readLine();
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(br != null){
				try{
					br.close();
				}catch(IOException el){
					
				}
			}
		}
	}
	
	public static ArrayList<String> readFile(String iFileName, boolean isRedudant, boolean isNULL){
		ArrayList<String> linesList = new ArrayList<String>();
		File iFile = new File(iFileName);
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(iFile));
			String line = br.readLine();
			while(line!=null){
				if(isRedudant==true && isNULL==true){
					linesList.add(line);
				}else if(isRedudant==true && isNULL==false){
					if(!line.equals(""))
						linesList.add(line);
				}else if(isRedudant==false && isNULL==true){
					if(!linesList.contains(line))
						linesList.add(line);
				}else if(isRedudant==false && isNULL==false){
					if(!line.equals("") && !linesList.contains(line))
						linesList.add(line);
				}
				line = br.readLine();
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(br != null){
				try{
					br.close();
				}catch(IOException el){
					
				}
			}
		}
		return linesList;
	}
	
	/**
	 * @map form: String_ArrayList<String>_Map
	 */
	public static void read_Str_List_Map(String iFileName, String label, 
			HashMap<String, ArrayList<String>> rel_cwList_map){
		String[] arr = null;
		String rel = null;
		String cw = null;
		String cwStr = null;
		String[] cwArr = null;
		ArrayList<String> cwList = null;
		File iFile = new File(iFileName);
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(iFile));
			String line = br.readLine();
			while(line!=null){
				arr = line.split(label);
				int len1 = arr.length;
				if(len1==2){
					rel = arr[0];
					cwStr = arr[1];
					cwArr = cwStr.split(" ");
					int len2 = cwArr.length;
					cwList = new ArrayList<String>();
					for(int i=0; i<len2; i++){
						cw = cwArr[i];
						cwList.add(cw);
					}
				}
				rel_cwList_map.put(rel, cwList);
				line = br.readLine();
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(br != null){
				try{
					br.close();
				}catch(IOException el){
					
				}
			}
		}
	}
	
	/**
	 * @map form: String_ArrayList<String>_Map
	 */
	public static void read_Str_Arr_Map(String iFileName, String labelMap, String labelArr, 
			HashMap<String, String[]> str_arr_map){
		int indexMap = -1;
		File iFile = new File(iFileName);
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(iFile));
			String line = br.readLine();
			while(line!=null){
				indexMap = line.indexOf(labelMap);
				
				if(indexMap!=-1){
					String str = line.substring(0, indexMap);
					String arrStr = line.substring(indexMap+labelMap.length(), line.length());
					String[] arr = arrStr.split(labelArr);
					str_arr_map.put(str, arr);
				}
				
				line = br.readLine();
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(br != null){
				try{
					br.close();
				}catch(IOException el){
					
				}
			}
		}
	}
	
	public static void readExisted(String oFileDir, ArrayList<String> existedList) throws Exception{
		String existed = "";
		ArrayList<String> dirExistedList = new ArrayList<String>();
		dirExistedList = common.Read.readDir(oFileDir);
		for(String dir : dirExistedList){
			String tmp = oFileDir+"//"+dir;
			existed = dir;
			ArrayList<String> fileExistedDir = new ArrayList<String>();
			fileExistedDir = common.Read.readDir(tmp);
			for(String file : fileExistedDir){
				existed = dir+"//"+file;
				existedList.add(existed);
			}
		}
	}
	
	public static void readHashMapStrList(String iFileName, HashMap<String, ArrayList<String>> str_list_map){
		///m/06v_1nd===>[football_position, ?????]
		String label_beg = "===>[";
		String label_end = "]";
		
		File iFile = new File(iFileName);
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(iFile));
			String line = br.readLine();
			while(line!=null){
	//			System.out.println(line);
				ArrayList<String> valList = new ArrayList<String>();
				int index_beg = line.indexOf(label_beg);
				int index_end = line.lastIndexOf(label_end);
				if(index_beg!=-1 && index_end!=-1 && index_beg+label_beg.length()<index_end){
					String key = line.substring(0, index_beg);
					String valStr = line.substring(index_beg+label_beg.length(), index_end);
					String[] valArr = valStr.split(", ");
					int arrLen = valArr.length;
					for(int i=0; i<arrLen; i++){
						valList.add(valArr[i]);
					}
					str_list_map.put(key, valList);
				}
				
				line = br.readLine();
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(br != null){
				try{
					br.close();
				}catch(IOException el){
					
				}
			}
		}
	}
	
	public static void readHashMapStrList(String iFileName, 
			HashMap<String, ArrayList<String>> str_list_map, 
			String label1, String label2){
		///m/06v_1nd===>[football_position, ?????]
		
		File iFile = new File(iFileName);
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(iFile));
			String line = br.readLine();
			while(line!=null){
	//			System.out.println(line);
				ArrayList<String> valList = new ArrayList<String>();
				int index1 = line.indexOf(label1);
				
				if(index1!=-1){
					String key = line.substring(0, index1);
					String valStr = line.substring(index1+label1.length(), line.length());
					String[] valArr = valStr.split(label2);
					int arrLen = valArr.length;
					for(int i=0; i<arrLen; i++){
						valList.add(valArr[i]);
					}
					str_list_map.put(key, valList);
				}
				
				line = br.readLine();
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(br != null){
				try{
					br.close();
				}catch(IOException el){
					
				}
			}
		}
	}
	
	public static void readHashMapStrInt(String iFileName, HashMap<String, Integer> str_int_map){
		///automotive/us_fuel_economy	15005
		File iFile = new File(iFileName);
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(iFile));
			String line = br.readLine();
			while(line!=null){
				if(!line.equals("")){
					String[] arr = line.split("\t");
					String key = arr[0];
					String valStr = arr[1];
					int valInt = Integer.parseInt(valStr);
					str_int_map.put(key, valInt);
				}
				
				line = br.readLine();
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(br != null){
				try{
					br.close();
				}catch(IOException el){
					
				}
			}
		}
	}
	
	public static void readHashMapStrInt(String iFileName, HashMap<String, Integer> str_int_map, boolean isSpecial){
		///automotive/us_fuel_economy	15005
		File iFile = new File(iFileName);
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(iFile));
			String line = br.readLine();
			int count = 1;
			while(line!=null){
				if(count%100000==0)
					System.out.println("finish "+count);
				if(!line.equals("")){
					String[] arr = line.split("\t");
					String key = arr[0];
					String valStr = arr[1];
					int valInt = Integer.parseInt(valStr);
					if(valInt!=0)
						str_int_map.put(key, valInt);
				}
				
				count++;
				line = br.readLine();
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(br != null){
				try{
					br.close();
				}catch(IOException el){
					
				}
			}
		}
	}
}
