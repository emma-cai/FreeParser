package common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Print {
	
	public static void printPair(Pair<String, String> pair){
		System.out.println("<"+pair.getFirst()+", "+pair.getSecond()+">");
	}
	
	public static void printMap(HashMap map){
//		for(Object key : map.keySet()){
//			Object val = map.get(key);
//			if(val!=null)
//				System.out.println(key.toString()+"===>"+val.toString());
//			else
//				System.out.println(key.toString()+"===>"+"null");
//		}
	}
	
	public static void printMap(HashMap<String, ArrayList<String>> map, String label){
		for(Object key : map.keySet()){
			System.out.println(key.toString());
			System.out.println(label);
			ArrayList<String> list = map.get(key);
			if(list!=null){
				for(String ele : list){
					System.out.println(ele);
				}
				System.out.println();
			}
		}
	}
	
	public static void printList(List list, String split){
		for(Object l : list){
			System.out.println(l);
			System.out.println(split);
		}
	}
}
