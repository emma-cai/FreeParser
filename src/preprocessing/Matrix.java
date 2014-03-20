package preprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Matrix {
	public static void main(String[] args) throws NumberFormatException, IOException{
		String iFileName_matrix = "/home/qcai/Downloads/clueweb_rel_mining/all.prob_word_given_rel.grow-diag-final-and.txt";
		String iFileName_idx2Rel = "/home/qcai/Downloads/clueweb_rel_mining/idx2rel.txt";
		String iFileName_idx2Word = "/home/qcai/Downloads/clueweb_rel_mining/idx2word.txt";
		
		//get the index_word_map
		HashMap<Integer, String> idx_wrd_MAP = new HashMap<Integer, String>();
		readIdxStr(iFileName_idx2Word, idx_wrd_MAP);
		System.out.println(idx_wrd_MAP.size());
		
		//get the index_rel_map
//		HashMap<Integer, String> idx_rel_MAP = new HashMap<Integer, String>();
//		readIdxStr(iFileName_idx2Rel, idx_rel_MAP);
//		System.out.println(idx_rel_MAP.size());
		
		readMatrix(iFileName_matrix);
		
		//given a word, find the index
		String val = "director";
		int idx = returnIndexByMapValue(idx_wrd_MAP, val);
		System.out.println(val+"\t"+idx);
		
	}
	
	/**
	 * FInd index_string_MAP
	 */
	public static void readIdxStr(String iFileName, HashMap<Integer, String> idx_str_MAP){
		//truli 2035 202592 -9.80322
		File iFile = new File(iFileName);
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(iFile));
			String line = br.readLine();
			while(line!=null){
				int index_space = line.indexOf(" ");
				String str = line.substring(0, index_space);
				String idxS = line.substring(index_space+1, line.indexOf(" ", index_space+1));
				int idx = Integer.parseInt(idxS);
				idx_str_MAP.put(idx, str);
				
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
	
	public static int returnIndexByMapValue(HashMap<Integer, String> idx_str_MAP, String str){
		for(Integer key : idx_str_MAP.keySet()){
			String val = idx_str_MAP.get(key);
			if(val.equals(str)){
				return key;
			}
		}
		return -1;
	}
	
	public static void readMatrix(String iFileName) throws NumberFormatException, IOException{
		//truli 2035 202592 -9.80322
//		File iFile = new File(iFileName);
//		BufferedReader br = null;
//		try{
//			br = new BufferedReader(new FileReader(iFile));
//			String line = br.readLine();
//			int lineCount = 1;
//			while(line!=null){
//				System.out.print(lineCount+" ");
//				if(lineCount%100==0)
//					System.out.println();
//				
//				line = br.readLine();
//				lineCount++;
//			}
//		}catch(IOException ioe){
//			ioe.printStackTrace();
//		}finally{
//			if(br != null){
//				try{
//					br.close();
//				}catch(IOException el){
//					
//				}
//			}
//		}
		
		
		File f = new File(iFileName);  
        BufferedReader buf = new BufferedReader(new FileReader(f));  
        int[][] matrix= new int[10484][20000];  
        int line = 0;  
  
        String str = null;  
        while ((buf.read()) != -1) {  
        	str = buf.readLine();  
            String[] date = str.split(" ");  
            for (int i = 0; i < date.length; i++) {  
                matrix[line][i] = Integer.parseInt(date[i]);  
                System.out.print(matrix[line][i]);  
            }
            line++;
        }  
  
    }
}
