package common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;


public class Map {
	public static List<String> sortMapByValue(HashMap<String,Integer> map, boolean isAscending){  
        int size = map.size();  
        ArrayList<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(size);  
        list.addAll(map.entrySet());
        if(isAscending==true){
        	ValueComparatorAscending vcAscending = new ValueComparatorAscending(); 
        	Collections.sort(list, vcAscending);
        }else{
        	ValueComparatorDescending vcDescending = new ValueComparatorDescending(); 
        	Collections.sort(list, vcDescending);
        }
        final List<String> keys = new ArrayList<String>(size);  
        for (int i = 0; i < size; i++) {  
            keys.add(i, list.get(i).getKey());  
        }
        
        return keys;
    }
	
	public static void main(String[] args){
		HashMap<String,Integer> first = new HashMap<String,Integer>();  
        first.put("20030120" , new Integer (56));    
        first.put("20030118" , new Integer (19));    
        first.put("20030125" , new Integer (25));    
        first.put("20030122" , new Integer (32));    
        first.put("20030117" , new Integer (67));    
        first.put("20030123" , new Integer (34));    
        first.put("20030124" , new Integer (42));    
        first.put("20030121" , new Integer (19));    
        first.put("20030119" , new Integer (98));  
        List<String> cixu = sortMapByValue((HashMap<String,Integer>) first, false);  
        for(String s : cixu){  
            System.out.println(s+"===>"+first.get(s));  
        }  
	}

//	public static ArrayList<String> sortMapByValue(
//			HashMap<String, Double> map, boolean isAscending) {
//		 int size = map.size();  
//	        ArrayList<Entry<String, Double>> list = new ArrayList<Entry<String, Double>>(size);  
//	        list.addAll(map.entrySet());
//	        if(isAscending==true){
//	        	ValueComparatorDoubleAscending vcAscending = new ValueComparatorDoubleAscending(); 
//	        	Collections.sort(list, vcAscending);
//	        }else{
//	        	ValueComparatorDoubleDescending vcDescending = new ValueComparatorDoubleDescending(); 
//	        	Collections.sort(list, vcDescending);
//	        }
//	        final List<String> keys = new ArrayList<String>(size);  
//	        for (int i = 0; i < size; i++) {  
//	            keys.add(i, list.get(i).getKey());  
//	        }
//	        
//	        return keys;
//	}
}

class ValueComparatorAscending implements Comparator<Entry<String, Integer>>{  
    public int compare(Entry<String, Integer> mp1, Entry<String, Integer> mp2)   
    {  
        return mp1.getValue() - mp2.getValue();  
    }  
}


class ValueComparatorDescending implements Comparator<Entry<String, Integer>>{  
    public int compare(Entry<String, Integer> mp1, Entry<String, Integer> mp2)   
    {  
        return mp2.getValue() - mp1.getValue();  
    }  
} 