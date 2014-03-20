package common;

import java.util.ArrayList;

public class List {
	public static ArrayList<String> merge(ArrayList<String> list1, ArrayList<String> list2){
		ArrayList<String> list = new ArrayList<String>();
		for(String s : list1){
			list.add(s);
		}
		for(String s : list2){
			list.add(s);
		}
		return list;
	}
	
	public static ArrayList<String> copy(ArrayList<String> list){
		ArrayList<String> copiedList = new ArrayList<String>();
		for(String s : list){
			copiedList.add(s);
		}
		return copiedList;
	}
	
//	public static ArrayList<String> randomList(ArrayList<String> list){
//		ArrayList<String> newList = new ArrayList<String>();
//		
//	}
}
