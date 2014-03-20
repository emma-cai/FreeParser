package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperationsInFB {
	/**
	 * Given a relation string, to tell the type: {"simple", "mediator", "midvalue"}
	 * @param relStr, eg: "/film/film@directed_by", "/location/location_statistic/population&/measurement/type@float", "/award/award_honor@award@award_winner@..."
	 * @return
	 */
	public static String isSimMedMid(String relStr){
		String labelOfAND = "&";
		String labelOfAT = "@";
		int indexOfAND = relStr.indexOf(labelOfAND);
		if(indexOfAND==-1){
			int indexOfAT = relStr.indexOf(labelOfAT);
			String rest = relStr.substring(indexOfAT+1, relStr.length());
			indexOfAT = rest.indexOf(labelOfAT);
			if(indexOfAT==-1){
				return "simple";
			}else{
				return "mediator";
			}
		}else{
			return "midvalue";
		}
	}
	
	/**
	 * read relation and its expected type information
	 * @param iFileName
	 */
	public static void readMEMap(String iFileName, HashMap<String, String> pro_me_map)
	{
		String key = null;
		String value = null;
		ArrayList<String> valsList = null;
		String[] kw_arr = null;
		String[] vs_arr = null;
		
		File iFile = new File(iFileName);
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(iFile));
			String line = br.readLine();
			while(line!=null)
			{	
				valsList = new ArrayList<String>();
				kw_arr = line.split(" = ");
				
				key = kw_arr[0];
				value = kw_arr[1];
				if(!value.equals("null") && !value.equals("")){
					vs_arr = value.split(" : ");
					pro_me_map.put(key, vs_arr[1]);
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

	/**
	 * read relation and its expected type information, but make sure that the expected type is the superType
	 * @param iFileName
	 */
	public static void readMEMap(String iFileName, 
			HashMap<String, String> subSuperTypeMap, 
			HashMap<String, String> pro_me_map)
	{
		String key = null;
		String value = null;
		String expectedType = null;
		String superType = null;
		ArrayList<String> valsList = null;
		String[] kw_arr = null;
		String[] vs_arr = null;
		
		File iFile = new File(iFileName);
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(iFile));
			String line = br.readLine();
			while(line!=null)
			{	
				valsList = new ArrayList<String>();
				kw_arr = line.split(" = ");
				
				key = kw_arr[0];
				value = kw_arr[1];
				if(!value.equals("null") && !value.equals("")){
					vs_arr = value.split(" : ");
					expectedType = vs_arr[1];
					superType = subSuperTypeMap.get(expectedType);
					pro_me_map.put(key, superType);
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
	
	/**
	 * read the map for subType===>superType
	 * @param iFileName
	 * @param subSuperTypeMap
	 */
	public static void readSubSuperType(String iFileName, HashMap<String, String> subSuperTypeMap){
//		(
//		/type/int
//		(/transportation/bridge /location/location)
		String subType = null;
		String superType = null;
		String tmp = null;
		String[] tmpArr = null;
		File iFile = new File(iFileName);
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(iFile));
			String line = br.readLine();
			while(line!=null){	
				if(!line.equals("(") && !line.equals(")")){
					if(line.startsWith("(")){
						tmp = line.substring(1, line.length()-1);
						tmpArr = tmp.split(" ");
						subType = tmpArr[0];
						superType = tmpArr[1];
						subSuperTypeMap.put(subType, superType);
					}else{
						subType = superType = line;
						subSuperTypeMap.put(subType, superType);
					}
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
	
	/**
	 * read the stopwords list
	 * @param iFileName
	 * @param stopWordsList
	 */
	public static void readStopWordsList(String iFileName, ArrayList<String> stopWordsList)
	{
		File iFile = new File(iFileName);
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(iFile));
			String line = br.readLine();
			while(line!=null)
			{				
				if(!stopWordsList.contains(line)){
					stopWordsList.add(line);
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
	
	/**
	 * Get "master_reverse_map" and "reverse_master_map"
	 * @param iFileNameMasterReverseMap
	 * @param masterReverseMap
	 * @param reverseMasterMap
	 */
	public static void readMasterReverseMap(String iFileName,
			HashMap<String, String> master_reverse_map,
			HashMap<String, String> reverse_master_map) {
		// TODO Auto-generated method stub
		File iFile = new File(iFileName);
		BufferedReader br = null;
		String label = "=====>";
		String master = null;
		String reverse = null;
		try{
			br = new BufferedReader(new FileReader(iFile));
			String line = br.readLine();
			while(line!=null){				
				int index = line.indexOf(label);
				master = line.substring(0, index);
				reverse = line.substring(index+label.length(), line.length());
				master_reverse_map.put(master, reverse);
				reverse_master_map.put(reverse, master);
				
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
	 *	Get english-names of entities listed in FixedNPList
	 */
	public static void readFixedNPList(String iFileName, ArrayList<String> fixedNPList) {
		//the beastie boys :- NP : /m/0116j8:/music/track
		String label = " :- ";
		String fixedNP = "";
		File iFile = new File(iFileName);
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(iFile));
			String line = br.readLine();
			while(line!=null){				
				int index = line.indexOf(label);
				fixedNP = line.substring(0, index);
				if(!fixedNPList.contains(fixedNP))
					fixedNPList.add(fixedNP);
				
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
	 * delete fixed-np
	 */
	public static String deleteFixedNP(String sen, ArrayList<String> fixedNPList)
	{
		String res = null;
		String fixedNP = null;
		int index = -1;
		for(int i=0; i<fixedNPList.size(); i++)
		{
			fixedNP = fixedNPList.get(i);
			if(regularExp(sen, fixedNP)){
				index = sen.indexOf(fixedNP);
				
				if(index!=-1){
					res = sen.substring(0, index)+sen.substring(index+fixedNP.length(), sen.length());
					sen = res.trim();
					if(sen.contains("  "))
						sen = sen.replaceAll("  ", " ");
				}
			}
			
		}
		return sen;
	}
	
	public static boolean regularExp(String snippet, String match){
		String regex = "\\b"+match+"\\b";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(snippet);
        if(m.find()){
        	return true;
        }
        return false;
    }
	
	/**
	 * Is the argument string containing special characters?
	 * @param str
	 * @return
	 */
	public static boolean isContainSpecialChars(String str)
	{
		if(str==null || str.equals(""))
			return true;
		
		String regEx="[âåäã©¡ð¸ð•£ñ¾²°ìí`~!.@#$%^&*+=|{}\"\\[\\]]";
		String res = "";
		Pattern p=Pattern.compile(regEx);     
		Matcher m=p.matcher(str);     
		if(m.find()){
			return true;
		}
		return false;
	}
	
	/**
	 * get the mainType of a relation
	 * @param args
	 */
	public static String getMainType(String rel, String isSimMedMid_str){
		String mainType = null;
		if(isSimMedMid_str.equals("simple") || isSimMedMid_str.equals("mediator")){
			int indexOfAT = rel.indexOf("@");
			if(indexOfAT==-1)
				indexOfAT = rel.lastIndexOf("/");
			String domainTypeStr = rel.substring(1, indexOfAT);
			int indexTmp = -1;
			indexTmp = domainTypeStr.indexOf("/");
			String oriD = domainTypeStr.substring(0, indexTmp);
			String oriT = domainTypeStr.substring(indexTmp+1, domainTypeStr.length());
			mainType = "/"+oriD+"/"+oriT;
			return mainType;
		}else if(isSimMedMid_str.equals("midvalue")){
			int indexOfAND = -1;
			String mainRel = null;
			String helpRel = null;
			indexOfAND = rel.indexOf("&");
			
			mainRel = rel.substring(0, indexOfAND);
			helpRel = rel.substring(indexOfAND+1, rel.length());
			
			//get original domain-type-pair list from mainRel
			int indexOfAT = mainRel.lastIndexOf("/");	//(for midType, I didn't use "@" in mainRel, but "@" is actually the last "/" in mainRel)
			String domainTypeStr = mainRel.substring(1, indexOfAT);
			int indexTmp = -1;
			indexTmp = domainTypeStr.indexOf("/");
			String oriD = domainTypeStr.substring(0, indexTmp);
			String oriT = domainTypeStr.substring(indexTmp+1, domainTypeStr.length());
			mainType = "/"+oriD+"/"+oriT;
			return mainType;
		}else{
			System.out.println("Freebase Relation Error!");
			return null;
		}	
	}
	
	/**
	 * Given the lambda expression, get the relation
	 * Lambda: (lambda $0 /common/topic (/fashion/textile@weave:t /en/tweed:/fashion/textile $0))
	 * Relation: /fashion/textile@weave
	 * @param semStr
	 * @return
	 */
	public static String getRelFromLambda(String lambda) {
		// TODO Auto-generated method stub
		String label = ":t";
		int end = lambda.indexOf(label);
		String tmp = lambda.substring(0, end);
		int beg = tmp.lastIndexOf("(");
		String rel = tmp.substring(beg+1, tmp.length());
		
		return rel;
	}

	/**
	 * Get HashMap, the key is str and the value is list
	 * Input: type=====>tw1 tw2 tw3
	 * Output: <type, <tw1, tw2, tw3>>
	 */
	public static void readStrListMap(String iFileName, String split, 
			HashMap<String, ArrayList<String>> str_list_map){
		String str = null;
		String listStr = null;
		String[] arr = null;
		String tmp = null;
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> linesList = common.Read.readFile(iFileName, false);
		int index = -1;
		for(String line : linesList){
			index = line.indexOf(split);
			if(index!=-1){
				list = new ArrayList<String>();
				str = line.substring(0, index);
				listStr = line.substring(index+split.length(), line.length());
				arr = listStr.split(" ");
				int len = arr.length;
				for(int i=0; i<len; i++){
					tmp = arr[i];
					if(!tmp.equals(" ") && !tmp.equals(""))
						list.add(tmp);
				}
				if(list!=null && list.size()!=0)
					str_list_map.put(str, list);
			}
		}
	}
	
	public static void main(String[] args){
		String lambda = "(lambda $0 /common/topic (/fashion/textile@weave:t /en/tweed:/fashion/textile $0))";
		String relation = getRelFromLambda(lambda);
		System.out.println(relation);
	}
}
