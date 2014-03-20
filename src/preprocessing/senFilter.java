package preprocessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class senFilter {
	public static ArrayList<String> stopWsList = new ArrayList<String>();
	public static int windowSize = 3;
	public static int numInsPerWriting = 5000;
	public static int numFilePerDir = 20;
	public static void main(String[] args){
		String iFileName_StopWs = "data/com/stopWordsList.txt";
		stopWsList = common.Read.readFile(iFileName_StopWs, false);
		
//		String iFileName = "data/clueweb/sample";
//		String oFileDir = "data/clueweb/sample_selected";
//		filterSens(iFileName, oFileDir);

		String mid1 = "/m/0b_h2q";	
		String mid2 = "/m/02cl1";
		String sen = "[FREEBASE mid=/m/02cl1 DENVER]DENVER[/FREEBASE], [FREEBASE mid=/m/01n4w COLORADO]COLORADO[/FREEBASE] test [FREEBASE mid=/m/01n4w COLORADO]COLORADO[/FREEBASE] (Post Time News)-- [FREEBASE mid=/m/0b_h2q Specialty Sports Venture]Specialty Sports Venture[/FREEBASE] ([FREEBASE mid=/m/0b_h2q SSV]SSV[/FREEBASE]) has become an even bigger player in ski rentals and retailing.";
		String filterMidSen = extrAndFilterMidSen(sen, mid1, mid2);
		System.out.println(filterMidSen);
		
//		String mid1 = "/m/04n31pb";
//		String mid2 = "/m/05k7sb";
//		String sen = "[FREEBASE mid=/m/0rgxp Wilmington]Wilmington[/FREEBASE], [FREEBASE mid=/m/05k7sb MA]MA[/FREEBASE] - February 2, 2007 - [FREEBASE mid=/m/04n31pb Security Innovation]Security";
//		String selectedPart = selectGoodPart(sen, mid1, mid2);
//		System.out.println(sen);
//		System.out.println(selectedPart);
	}
	
	public static void filterSens(String iFileName, String oFileDir){
		File iFile = new File(iFileName);
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(iFile));
			String line = br.readLine();
			String oFileDir_sub = null;
			int ins_count = 1;
			int file_count = 0;
			int dir_index = 1;
			int file_index = 1;
			ArrayList<String> selectedSenList = new ArrayList<String>();
			ArrayList<String> selectedPartList = new ArrayList<String>();
			ArrayList<String> selectedRelList = new ArrayList<String>();
			ArrayList<String> selectedMid1List = new ArrayList<String>();
			ArrayList<String> selectedMid2List = new ArrayList<String>();
			while(line!=null){
				
				String[] arr = line.split("\t");
				String rel = arr[0];
				if(!rel.startsWith("location.") && !rel.startsWith("base.") 
					&& !rel.startsWith("user.")){
					String mid1 = arr[1];
					String mid2 = arr[2];
					String sen = arr[3];
					mid1 = "/"+mid1.replace(".", "/");
					mid2 = "/"+mid2.replace(".", "/");
					//find sentences which could be good instances for learning
					String filterMidSen = extrAndFilterMidSen(sen, mid1, mid2);
					if(filterMidSen!=null && !filterMidSen.equals("")){
						String[] filterMidSenArr = filterMidSen.split(" ");
						int len = filterMidSenArr.length;
						if(filterMidSen!=null && !filterMidSen.equals("")
								&& len>=1 && len<=5){
							
							//for these candidate instances, select the good part for further processing
							String selectedPart = selectGoodPart(sen, mid1, mid2);
							
							
							//writeToFile
							if(ins_count%numInsPerWriting==0){
								if(file_count%numFilePerDir==0){
									oFileDir_sub = oFileDir+"/"+dir_index;
									dir_index++;
								}
								String oFileName = oFileDir_sub+"/"+file_index;
								System.out.println("Director="+(dir_index-1)+"\t\t\t"+"File="+file_index);
								writeToFile(oFileDir_sub, oFileName, false, selectedRelList, selectedMid1List, selectedMid2List, selectedSenList, selectedPartList);
								selectedSenList = new ArrayList<String>();
								selectedPartList = new ArrayList<String>();
								selectedRelList = new ArrayList<String>();
								selectedMid1List = new ArrayList<String>();
								selectedMid2List = new ArrayList<String>();
								file_index++;
								file_count++;
							}else{
								if(selectedPart!=null && !selectedPart.equals("")){
									selectedSenList.add(sen);
									selectedPartList.add(selectedPart);
									selectedRelList.add(rel);
									selectedMid1List.add(mid1);
									selectedMid2List.add(mid2);
								}
							}
							ins_count++;
							
							
							
							
//							if(count%numInsPerWriting==0){
//								System.out.println("processing "+count);
//								
//								if(count==numInsPerWriting){
//									writeToFile(oFileDir_sub, oFileName, false, selectedRelList, selectedMid1List, selectedMid2List, selectedSenList, selectedPartList);
//								}else{
//									writeToFile(oFileDir_sub, oFileName, true, selectedRelList, selectedMid1List, selectedMid2List, selectedSenList, selectedPartList);
//								}
//								selectedSenList = new ArrayList<String>();
//								selectedPartList = new ArrayList<String>();
//								selectedRelList = new ArrayList<String>();
//								selectedMid1List = new ArrayList<String>();
//								selectedMid2List = new ArrayList<String>();
//							}else{
//								if(selectedPart!=null && !selectedPart.equals("")){
//									selectedSenList.add(sen);
//									selectedPartList.add(selectedPart);
//									selectedRelList.add(rel);
//									selectedMid1List.add(mid1);
//									selectedMid2List.add(mid2);
//								}
//							}
//							count++;
							
//							System.out.println(mid1);
//							System.out.println(mid2);
//							System.out.println(rel);
//							System.out.println(selectedPart);
//							System.out.println();
						}
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
	 * Given a sentence, mid1 and mid2, find the filtered mid-sentence between mid1 and mid2
	 */
	public static String extrAndFilterMidSen(String sen, String mid1, String mid2){
		//get mid-sentence between two entities
		String match1 = "[/FREEBASE]";
		String match2 = "[FREEBASE ";
		int index1 = sen.indexOf(mid1);
		int index2 = sen.indexOf(mid2);
		int minIndex = Math.min(index1, index2);
		int maxIndex = Math.max(index1, index2);
		int indexBeg = sen.indexOf(match1, minIndex);
		int indexEnd = sen.lastIndexOf(match2, maxIndex);
		String midSen = sen.substring(indexBeg+match1.length(), indexEnd).trim();
		
		
		//filter out the stop-words
		String filterMidSen = midSen;
		filterMidSen = rmvStopWs(filterMidSen);
	//	System.out.println("After removing stopWs: "+filterMidSen);
		
		//filter out Freebase-entities
		filterMidSen = rmvFbEnt(filterMidSen);
	//	System.out.println("After removing freebase-entities: "+filterMidSen);
		
		//filter out the punctuation
		filterMidSen = rmvPunc(filterMidSen);
	//	System.out.println("After removing punctuation: "+filterMidSen);
		
		//filter out the entities (with capitalized character)
		filterMidSen = rmvEnt(filterMidSen);
//		System.out.println("After removing entities: "+filterMidSen);
		
		return filterMidSen;
	}
	
	/**
	 * remove freebase entities
	 * eg_input: , [FREEBASE mid=/m/0d060g Canada]Canada[/FREEBASE], and all over the 
	 * eg_output: , , and all over the 
	 */
	public static String rmvFbEnt(String sen){
		if(sen==null || sen.equals(""))
			return sen;
		
		String match1 = "[FREEBASE ";
		String match2 = "[/FREEBASE]";
		
//		int index1 = sen.indexOf(match1);
//		int index2 = sen.indexOf(match2);
//		if(index1==-1 || index2==-1)
//			return null;
//		
//		String bef = sen.substring(0, index1).trim();
//		String aft = sen.substring(index2+match2.length(), sen.length()).trim();
		
		String tmp = sen;
		int index1 = tmp.indexOf(match1);
		int index2 = tmp.indexOf(match2);
		while(index1!=-1 && index2!=-1){
			String bef = tmp.substring(0, index1).trim();
			String aft = tmp.substring(index2+match2.length(), tmp.length()).trim();
			
			tmp = bef+" "+aft;
			index1 = tmp.indexOf(match1);
			index2 = tmp.indexOf(match2);
		}
		
		return tmp;
	}
	
	/**
	 * remove stop-words
	 */
	public static String rmvStopWs(String sen){
		if(sen==null || sen.equals(""))
			return sen;
		
		String filterSen = "";
		String[] arr = sen.split(" ");
		for(String ele : arr){
			String eleTmp = ele.toLowerCase();
			if(!stopWsList.contains(eleTmp)){
				filterSen += ele+" ";
			}
		}
		filterSen = filterSen.trim();
		return filterSen;
	}
	
	/**
	 * remove punctuation
	 */
	public static String rmvPunc(String sen){
		if(sen==null || sen.equals(""))
			return sen;
		
		String regEx="[Ä±Ãªâåäã©¡ð¸ð•£ñ¾²°ìí`~\\-!.@#$%^&'+=:(),{}\"\\[\\]]";
		String filterSen = common.Operator.replaceAllRegularExp(sen, regEx, "");
		filterSen = filterSen.trim();
		return filterSen;
	}
	
	/**
	 * remove entities (the words with capitalized character)
	 */
	public static String rmvEnt(String sen){
		if(sen==null || sen.equals(""))
			return sen;
		
		String filterSen = "";
		String[] arr = sen.split(" ");
		for(String ele : arr){
			char ch = ele.charAt(0);
			if(!(ch>='A' && ch<='Z'))
				filterSen += ele+" ";
		}
		
		filterSen = filterSen.trim();
		return filterSen;
	}
	
	/**
	 * select the good part: senBef(no more than three words)+senMid+senAft(no more than three words)
	 */
	public static String selectGoodPart(String sen, String mid1, String mid2){
		String match1 = "[/FREEBASE]";
		String match2 = "[FREEBASE ";
		int index1 = sen.indexOf(mid1);
		int index2 = sen.indexOf(mid2);
		if(index1==-1 || index2==-1)
			return null;
		
		int minIndex = Math.min(index1, index2);
		int maxIndex = Math.max(index1, index2);
		int indexBegTmp = sen.lastIndexOf(match2, minIndex);
		int indexEndTmp = sen.indexOf(match1, maxIndex);
		if(indexBegTmp==-1 || indexEndTmp==-1)
			return null;
		
		String joinStrFromBef = "";
		String joinStrFromAft = "";
		String senBef = sen.substring(0, indexBegTmp).trim();
		String senAft = sen.substring(indexEndTmp+match1.length(), sen.length()).trim();
		//select senBef, which doesn't end with a punctuation and doesn't contain a freebase-entity
		if(senBef.endsWith(",") || senBef.endsWith(".") 
				|| senBef.endsWith(";") || senBef.endsWith(":")){	//if the senBef ends with a punctuation, don't consider it
			joinStrFromBef = "";
		}else{
			String[] joinArrFromBef = senBef.split(" ");
			int joinArrFromBefLen = joinArrFromBef.length;
			for(int i=joinArrFromBefLen-1; i>=0&&i>=joinArrFromBefLen-windowSize; i--){
				String ele = joinArrFromBef[i];
				if(ele.contains("]") || ele.contains("["))
					break;
				joinStrFromBef = ele+" "+joinStrFromBef;
			}
			joinStrFromBef = joinStrFromBef.trim();
		}
		
		//select senAft, which doesn't start with a punctuation, and doesn't contain a freebase-entity
		if(senAft.startsWith(",") || senAft.startsWith(".") 
				|| senAft.startsWith(";") || senAft.startsWith(":")){	//if the senAft starts with a punctuation, don't consider it
			joinStrFromAft = "";
		}else{
			String[] joinArrFromAft = senAft.split(" ");
			int joinArrFromAftLen = joinArrFromAft.length;
			for(int i=0; i<joinArrFromAftLen-1&&i<=windowSize; i++){
				String ele = joinArrFromAft[i];
				if(ele.contains("[") || ele.contains("]"))
					break;
				
				joinStrFromAft = joinStrFromAft+" "+ele;
			}
			joinStrFromAft = joinStrFromAft.trim();
		}
		String midStr = sen.substring(indexBegTmp, indexEndTmp+match1.length());
		String selectedPart = joinStrFromBef+" "+midStr+" "+joinStrFromAft;
		selectedPart = selectedPart.trim();
		return selectedPart;
	}
	
	/**
	 * write information to output file
	 */
	public static void writeToFile(String oFileDir, String oFileName, boolean isAddWriting, 
			ArrayList<String> relList, ArrayList<String> mid1List, 
			ArrayList<String> mid2List, ArrayList<String> list1, ArrayList<String> list2){
		int len1 = list1.size();
		int len2 = list2.size();
		if(len1!=len2){
			System.err.println("Errors in data");
			System.exit(-1);
		}
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
			for(int i=0; i<len1; i++){
				bw.write(relList.get(i));
				bw.newLine();
				bw.write(mid1List.get(i));
				bw.newLine();
				bw.write(mid2List.get(i));
				bw.newLine();
				bw.write(list1.get(i));
				bw.newLine();
				bw.write(list2.get(i));
				bw.newLine();
				bw.newLine();
			}
			bw.flush();
			bw.close();
		}catch(IOException ioe){
			System.out.println("Fail to create the file " + oFileName + "!");
		}
	}
}
