package preprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class test {
	public static void main(String[] args){
		String iFileName = "data/clueweb/sample_selected/1/2";
		get_OS_MR(iFileName);
	}
	
	public static void get_OS_MR(String iFileName){
		HashMap<String, ArrayList<String>> os_mr_MAP = new HashMap<String, ArrayList<String>>();
		File iFile = new File(iFileName);
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(iFile));
			String line = br.readLine();
			
			while(line!=null){
				String rel = line;
				String mid1 = br.readLine();
				String mid2 = br.readLine();
				String sen = br.readLine();
				String selectedPart = br.readLine();
				br.read();
				String relM12 = "("+rel+":t "+mid1+", "+mid2+")";
				
				ArrayList<String> mr_LIST = new ArrayList<String>();
				if(os_mr_MAP.containsKey(selectedPart)){
					mr_LIST = os_mr_MAP.get(selectedPart);
					if(isExisted(mr_LIST, relM12)==false){
						mr_LIST.add(relM12);
					}
				}else{
					mr_LIST = new ArrayList<String>();
					mr_LIST.add(relM12);
				}
				os_mr_MAP.put(selectedPart, mr_LIST);
//				System.out.println(relM12);
//				System.out.println(selectedPart);
				
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
		common.Print.printMap(os_mr_MAP, "===>");
	}
	
	public static boolean isExisted(ArrayList<String> mr_LIST, String relM12){
		for(String tmp : mr_LIST){
			if(isSame(tmp, relM12)==true){
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean isSame(String relM_1, String relM_2){
		String rel1 = relM_1.substring(1, relM_1.indexOf(":t"));
		String mt1 = relM_1.substring(relM_1.indexOf(":t ")+3, relM_1.indexOf(", "));
		String et1 = relM_1.substring(relM_1.indexOf(", ")+2, relM_1.length()-1);
		
		String rel2 = relM_2.substring(1, relM_2.indexOf(":t"));
		String mt2 = relM_2.substring(relM_2.indexOf(":t ")+3, relM_2.indexOf(", "));
		String et2 = relM_2.substring(relM_2.indexOf(", ")+2, relM_2.length()-1);
		
		if(rel1.equals(rel2) && (mt1.equals(mt2) && et1.equals(et2)))
			return true;
		
		if((mt1.equals(et2) && et1.equals(mt2)))
			return true;
		
		return false;
	}
}
